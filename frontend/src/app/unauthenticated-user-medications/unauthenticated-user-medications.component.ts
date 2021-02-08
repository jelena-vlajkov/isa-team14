import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { Patient } from '@app/model/users/patient/patient';
import { PatientService } from '@app/service/patient/patient.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/service/user';
import { Role } from '@app/model/users';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SupplierService } from '@app/service/supplier/supplier.service';


@Component({
  selector: 'app-unauthenticated-user-medications',
  templateUrl: './unauthenticated-user-medications.component.html',
  styleUrls: ['./unauthenticated-user-medications.component.css']
})
export class UnauthenticatedUserMedicationsComponent implements OnInit {
  public showinfo : boolean;
  public selectedMedication : Medication;
  public patient : Patient;
  public patientHere : boolean = false;
  public noone : boolean = true;
  public sysAdmin : SystemAdmin;
  public supplier : Supplier;
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(private supplierService : SupplierService, private systemAdmin : SysadminRegistrationService, private router: Router, private patientService: PatientService, public medicationService : MedicationService, private auth : AuthenticationService) { 
    this.showinfo=false;
  }

  public medications : Medication[] = new Array();

  ngOnInit(): void {
    this.getAllMedications();
    // try{
    //   console.log(this.auth.getUserValue().role == Role.Patient);

    // }catch(error){

    // }
    // // try {
    // //   this.loadPatient();
    // //   this.patientHere = true;
    // //   console.log(this.auth.getUserValue());
    // // } catch (error) {
    // //   alert('not logged in poy bitches');
    // // }
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
  showMoreInfo(m){
    console.log(m);
    this.showinfo = true;
    this.selectedMedication = m;
  }
  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }

}
