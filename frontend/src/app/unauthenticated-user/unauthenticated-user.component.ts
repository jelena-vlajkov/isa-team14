import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {Pharmacy} from '../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { Patient } from '@app/model/users/patient/patient';
import { Role } from '@app/model/users';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { Router } from '@angular/router';
import { PatientService } from '@app/service/patient/patient.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-unauthenticated-user',
  templateUrl: './unauthenticated-user.component.html',
  styleUrls: ['./unauthenticated-user.component.css']
})
export class UnauthenticatedUserComponent implements AfterViewInit {
  public patient : Patient;
  public patientHere : boolean = false;
  public noone : boolean = true;
  public sysAdmin : SystemAdmin;
  public supplier : Supplier;
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(private auth : AuthenticationService,private supplierService : SupplierService, private systemAdmin : SysadminRegistrationService, private router: Router, private patientService: PatientService, private pharmacyService : PharmacyService, public medicationService : MedicationService) {

  }
  public pharmacies : Pharmacy[] = new Array();
  public pharmaciesCopy : Pharmacy[] = new Array();
  public medications : Medication[] = new Array();
  pharmacy:boolean;
  medcs:boolean;


  ngOnInit(): void {
    let user = this.auth.currentUserValue;
    console.log(user)
    if (user !== null) {
      if (user !== undefined) {
        if (user.role === 'Pharmacist' || user.role === 'Dermatologist') {
          this.router.navigate(["/dashboard"])
        }
        if (user.role ==='PharmacyAdmin'){
          this.router.navigate(["/pharmacy-admin-profile"])
        }
      }
    }

    this.pharmacy = false;
    this.medcs = false;
    try{
      if(this.isPatient()){
          this.loadPatient();
        }else if (this.isAdmin()){
          this.loadAdmin();
        }else if(this.isSupplier()){
          this.loadSupplier();
        }

    }catch(error){
      console.log('UPUCACU SE VISE AAAAAAAA');
      console.log('ok radi sve kul idegasnamax');
    }


  }
  checkLoggedInUser(){
    return this.auth.getUserValue();
  }
  isPatient() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Patient;
  }
  isSupplier() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Supplier;
  }
  isAdmin() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.SysAdmin;
  }

  logout(){
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  loadPatient(){
    this.patientService.getPatientById(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.patient = data;
      });
  }

  loadAdmin(){
    this.systemAdmin.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.sysAdmin = data;
      });
  }
  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.supplier = data;
      });
  }

  enablePharmacy(){
    this.pharmacy = true;
    this.medcs = false;

    this.getAllPharmacies();
  }

  enableMedcs(){
    this.pharmacy = false;
    this.medcs = true;

    this.getAllMedications();

  }

  getGradeValues(event) {

    console.log(event.value);
    if (event.value == 1){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGrade.count() >= 0 &&
         p.averageGrade.count() <=1);
    }else if(event.value == 2){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGrade.count() >= 1 &&
         p.averageGrade.count() <=2);
    }else if(event.value == 3){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGrade.count() >= 2 &&
         p.averageGrade.count() <=3);
    }
    else if(event.value == 4){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGrade.count() >= 3 &&
         p.averageGrade.count() <=4);
    } else if(event.value == 5){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGrade.count() >= 4 &&
         p.averageGrade.count() <=5);
    }else {
      this.pharmacies = this.pharmaciesCopy;
    }
  }

  getAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
        this.pharmaciesCopy = data;
      });
  }


  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }

  findByName(inputName : String){
    this.pharmacyService.findByName(inputName).subscribe(data =>
      {
        this.pharmacies = data;
      })
  }

  findByAddress(inputAdr : String){
    this.pharmacyService.findByAddress(inputAdr).subscribe(data =>
      {
        this.pharmacies = data;
      })
  }

  sortData(sort: Sort){
    const data = this.pharmacies.slice();
    if (!sort.active || sort.direction === '') {
      this.pharmacies = data;
      return;
    }

    this.pharmacies = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return compare(a.averageGrade.count(), b.averageGrade.count(), isAsc);
        default: return 0;
      }
     });
  }


}

function compare(a: Number | string, b: Number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
