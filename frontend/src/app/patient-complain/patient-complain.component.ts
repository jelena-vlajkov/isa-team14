import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Complaint } from '@app/model/membershipinfo/complaint';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { Dermatologist } from '@app/model/users/dermatologist/dermatologist';
import { Patient } from '@app/model/users/patient/patient';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import { ComplaintsService } from '@app/service/complaints/complaints.service';
import { DermatologistService } from '@app/service/dermatologist/dermatologist.service';
import { PatientService } from '@app/service/patient/patient.service';
import { PharmacistService } from '@app/service/pharmacist/pharmacist.service';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { AuthenticationService } from '@app/service/user';
interface ToWhoToComplain {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-patient-complain',
  templateUrl: './patient-complain.component.html',
  styleUrls: ['./patient-complain.component.css']
})
export class PatientComplainComponent implements OnInit {
  public patient : Patient; 
  public dermatologists : Dermatologist[];
  public pharmacists : Pharmacist[];
  public pharmacies : Pharmacy[];
  registerComplain: FormGroup;
  public chosenEntity : boolean = false;
  public entitySelected : boolean = false;
  public enterText:boolean = false;

  toWhomToComplain: ToWhoToComplain[] = [
    {value: 'dermatologist', viewValue: 'Dermatoligst'},
    {value: 'pharmacist', viewValue: 'Pharmacist'},
    {value: 'pharmacy', viewValue: 'Pharmacy'}
  ];  
  public ent : String = "";
  public comment : String ="";
  public personModel;
  public source;
  public person;
  public pharmDerm: boolean;
  public personSelected : boolean;
  public firstNotSelected : boolean = false;
  public complaint : Complaint;
  constructor(private pharmacyService : PharmacyService,private router: Router, private complaintService : ComplaintsService, private authenticationService:AuthenticationService,private patientService : PatientService, private dermatologistService : DermatologistService,private _formBuilder: FormBuilder, private pharmacistService : PharmacistService) { }

  ngOnInit(): void {
    this.loadPatient();
    // this.firstFormGroup = new FormGroup({
    //   "firstCtrl" : new FormControl(null, Validators.required)
    // });
    // this.secondFormGroup = new FormGroup({
    //   "secondCtrl" : new FormControl(null, Validators.required)
    // });
    this.registerComplain = new FormGroup({
    'entity' : new FormControl(null, Validators.required),
    'who' : new FormControl(null, Validators.required),
    'comment' : new FormControl(null, Validators.required)
    });
  }

  choseEntity(){
    if(this.registerComplain.controls.entity.value === 'dermatologist'){
      this.source = this.dermatologists;
      this.pharmDerm = true;
    }else if(this.registerComplain.controls.entity.value === 'pharmacist'){
      this.source = this.pharmacists;
      this.pharmDerm = true;
    }else{
      this.source = this.pharmacies;
      this.pharmDerm = false;    
    }
    this.entitySelected=true;
    this.chosenEntity=true;
  }
  checkEnt(){
    if(this.ent===""){
      return false;
    }
    return true;
  }
  checkPerson(){
    if(this.personModel===undefined){
      return false;
    }
    return true;
  }
  checkComment(){
    if(this.comment===""){
      return false;
    }
    return true;
  }
  choosePerson(){
    this.person = this.registerComplain.controls.who.value;
    this.personSelected=true;
    console.log(this.person);
    this.enterText=true;
  }

  loadPatient(){
    this.patientService.getPatientById(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.patient = data;
        console.log(this.patient);

      });
      this.dermatologistService.getDermatologistsToComplain(Number(localStorage.getItem('userId'))).subscribe(data=>
        {
          this.dermatologists = data;
          console.log(this.dermatologists);

        });
        this.pharmacistService.getPharmacistsToComplain(Number(localStorage.getItem('userId'))).subscribe(data=>
          {
            this.pharmacists = data;
            console.log(this.pharmacists);
  
          });
          this.pharmacyService.getPharmacyToComplain(Number(localStorage.getItem('userId'))).subscribe(data=>
            {
              this.pharmacies = data;
              console.log(this.pharmacists);
    
            });

  }
  sendComplaint(){
    // if(this.person===null || this.registerComplain.controls.comment.value===""){
    //   alert('Please fill the form again');
    //   this.registerComplain.reset();
    //   this.chosenEntity = false;
    //   this.entitySelected = false;
    //   this.enterText = false;
    //   this.personSelected=false;
    //   this.entitySelected=false;
    // }else{

    // }

    
    this.complaint = new Complaint(null, this.patient, this.comment, this.personModel, this.registerComplain.controls.entity.value, false);
    console.log(this.complaint);
    this.complaintService.postComplaint(this.complaint).subscribe(
      res=>{
        alert('Success');
        this.router.navigate(['/userProfile']);
      },
      error=>{
        alert("Fail");
      });
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);

  }
}
