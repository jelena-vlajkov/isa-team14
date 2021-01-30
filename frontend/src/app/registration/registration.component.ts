import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { Proba } from './../service/Proba';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Address } from '../model/address/address';
import { RegistrationService } from '../service/registration/registration.service';
import { Patient } from '../model/patient/patient';
import { Gender } from '../model/patient/gender';
import { Role } from '../model/users/role';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;
  minDateOfBirth : Date;
  maxDateOfBirth : Date;
  
  minDatePolicy : Date;
  maxDatePolicy : Date;

  formData : FormData;
  address : Address;
  name : string;
  surname : string;
  phone : string;
  email : string;
  password : string;
  confirmPassword : string;
  gender : Gender;
  selectedGender;
  selectedDate;
  dateOfBirth : Date;
  public patient : Patient;
  public places : String;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private registrationService : RegistrationService) { }

  ngOnInit(): void {    
    this.registerForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'password1' : new FormControl(null, Validators.required),
      'password2' : new FormControl(null, Validators.required),
      'gender' : new FormControl(null, Validators.required),
      'date' : new FormControl(null, Validators.required)
    });
    console.log("OM<GGSDGSD");


  }
  register(){
    this.name = this.registerForm.value.name;
    this.surname = this.registerForm.value.surname;
    this.phone = this.registerForm.value.telephone;
    this.email = this.registerForm.value.email;
    this.password = this.registerForm.value.password1;
    this.confirmPassword = this.registerForm.value.password2;
    this.address = this.googleplaces.address;
    this.gender = this.selectedGender;
    this.dateOfBirth = this.selectedDate;
    console.log(this.dateOfBirth);
    
    var role : Role;
    role = Role.Patient
    var auths : Number[] = new Array();
    console.log(this.address);
    this.patient = new Patient(this.name, this.surname, this.dateOfBirth, this.phone, this.email, this.password, this.gender, this.address, role, auths);
    console.log(JSON.parse(JSON.stringify(this.patient)));
    if(this.passwordValid()){
      this.registrationService.registerPatient(this.patient).subscribe(
        res=>{
          this.registerForm.reset();
          this.googleplaces = null;
          alert('Success');
        },
        error=>{
          alert("Fail")
        }
      )
    }else{
      alert('Passwords do not match');
    }
    
  }
  passwordValid(){
    return this.password==this.confirmPassword;
  }
}
