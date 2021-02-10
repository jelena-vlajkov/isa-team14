import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { Router } from '@angular/router';
import { PatientService } from '@app/service/patient/patient.service';
import { Patient } from '@app/model/users/patient/patient';
import { Subscription } from '@app/model/membershipinfo/subscription';

@Component({
  selector: 'app-pharmacy-profile-mock',
  templateUrl: './pharmacy-profile-mock.component.html',
  styleUrls: ['./pharmacy-profile-mock.component.css']
})
export class PharmacyProfileMockComponent implements OnInit {
  public pharmacy : Pharmacy;
  public patient : Patient;
  public subscription : Subscription;
  public usersSubs : Subscription[];

  public isloggedIn : boolean;
  public isSubscribed : boolean = false;
  public id : Number;
  constructor(private patientService:PatientService,private router: Router, private location:Location, private pharmacyService : PharmacyService) {
      if(localStorage.getItem('userId')===null){
        this.isloggedIn = false;
      }else{
        this.isloggedIn = true;

          localStorage.removeItem('currentPharmacy');

          localStorage.setItem('currentPharmacy', String(history.state.id))
          console.log(history.state.id);
          this.loadPatient();
          this.loadPharmacy();
          this.loadUsersSubscriptions();
      }

   }

  ngOnInit(): void {
    // this.id = history.state.id;
 

  }

  loadUsersSubscriptions(){
    this.pharmacyService.getAllUsersSubscriptions(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.usersSubs = data;

      });
  }
  loadPatient(){
    this.patientService.getPatientById(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.patient = data;

      });
  }
  loadPharmacy(){
    this.pharmacyService.getPharmacyById(Number(localStorage.getItem('currentPharmacy'))).subscribe(data =>
      {
        this.pharmacy = data;
      });
  }
  checkSubscribed(){
    this.isSubscribed=false;
    if(this.usersSubs.length>0){
      for(let i=0; i<this.usersSubs.length; i++){
        if(this.usersSubs[i].pharmacy.id === Number(localStorage.getItem('currentPharmacy'))){
          this.isSubscribed=true;
          return true;
        }
      }
    }
    return false;
  }
  unsubscribe(){
    this.subscription = new Subscription(this.patient, this.pharmacy);
    this.pharmacyService.unsubscribe(this.subscription).subscribe(
      res=>{
        alert('Succesfully unsubscribed');
        localStorage.removeItem('currentPharmacy');
        this.router.navigate(['/searchPharmacies']);
      },
      error=>{
        alert("Fail");
      });
      this.loadUsersSubscriptions();
      
  }
  subscribeToActions(){
    
    this.subscription = new Subscription(this.patient, this.pharmacy);
    this.pharmacyService.subscribe(this.subscription).subscribe(
      res=>{
        alert('Success');
        localStorage.removeItem('currentPharmacy');
        this.router.navigate(['/searchPharmacies']);
      },
      error=>{
        alert("Fail");
      });
  }
}


