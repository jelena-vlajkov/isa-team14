import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {Pharmacy} from '../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { AuthenticationService } from '@app/service/user';
import { Router } from '@angular/router';
import { Role } from '@app/model/users';
import { PatientService } from '@app/service/patient/patient.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { Patient } from '@app/model/users/patient/patient';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';

@Component({
  selector: 'app-unauthenticated-user-pharmacies',
  templateUrl: './unauthenticated-user-pharmacies.component.html',
  styleUrls: ['./unauthenticated-user-pharmacies.component.css']
})
export class UnauthenticatedUserPharmaciesComponent implements AfterViewInit {
  public patient : Patient;
  public patientHere : boolean = false;
  public noone : boolean = true;
  public sysAdmin : SystemAdmin;
  public supplier : Supplier;


  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(private auth: AuthenticationService, private supplierService : SupplierService, private systemAdmin : SysadminRegistrationService, private router: Router, private patientService: PatientService, private pharmacyService : PharmacyService) {

  }

  public pharmacies : Pharmacy[] = new Array();
  public pharmaciesCopy : Pharmacy[] = new Array();

  ngOnInit(): void {
    this.getAllPharmacies();
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

    // if(this.isPatient()){
    //   this.loadPatient();
    //   console.log(this.patient);
    // }else if (this.isAdmin()){
    //   this.loadAdmin();
    // }else if(this.isSupplier()){
    //   this.loadSupplier();
    // }

  }
  checkLoggedInUser(){
    return this.auth.getUserValue();
  }
  isPatient() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Patient;
  }
  isAdmin() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.SysAdmin;
  }
  isSupplier() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Supplier;
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

  getGradeValues(event) {
    console.log(event.value);
    if (event.value == 1){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGradeCount >= 0 &&
         p.averageGradeCount <=1);
    }else if(event.value == 2){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGradeCount >= 1 &&
         p.averageGradeCount <=2);
    }else if(event.value == 3){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGradeCount >= 2 &&
         p.averageGradeCount <=3);
    }
    else if(event.value == 4){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGradeCount >= 3 &&
         p.averageGradeCount <=4);
    } else if(event.value == 5){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.averageGradeCount >= 4 &&
         p.averageGradeCount <=5);
    }else {
      this.pharmacies = this.pharmaciesCopy;
    }
  }
  // goToProfile(pharmacy){
  //   this.router.navigate(['/pharmacy-profile?id='+pharmacy.id])
  // }
  routeToPharmacyProfileMock(p){
    var string = p.id.toString();
    this.router.navigateByUrl('/pharmacyProfileMock', {state: { id: string} });
  }
  getAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
        this.pharmaciesCopy = data;
      });

      console.log(this.pharmacies)
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
        case 'grade': return compare(a.averageGradeCount, b.averageGradeCount, isAsc);
        default: return 0;
      }
     });
  }


}

function compare(a: Number | string, b: Number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
