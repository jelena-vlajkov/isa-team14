import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Address } from '../model/address/address';
import { RegistrationService } from '../service/registration/registration.service';
import { Patient } from '../model/users/patient/patient';
import { Gender } from '../model/users/patient/gender';
import { Role } from '../model/users/role';
import { Router } from '@angular/router';

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

  constructor(private router: Router, private registrationService : RegistrationService) { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      'name' : new FormControl(null, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'email' : new FormControl(null, [Validators.required, Validators.email]),
      'telephone' : new FormControl(null,  [Validators.required, Validators.pattern("^[0-9]*$")]),
      'password1' : new FormControl(null, [Validators.required,Validators.minLength(8)]),
      'password2' : new FormControl(null,[Validators.required,Validators.minLength(8)]),
      'gender' : new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required)
    });
    this.maxDateOfBirth = new Date();
    this.minDateOfBirth = new Date();
    this.minDateOfBirth.setFullYear(this.minDateOfBirth.getFullYear() - 180);

  }
  register(){
    this.name = this.registerForm.value.name;
    this.surname = this.registerForm.value.surname;
    this.phone = this.registerForm.value.telephone;
    this.email = this.registerForm.value.email;
    this.password = this.registerForm.value.password1;
    this.confirmPassword = this.registerForm.value.password2;
    //this.address = this.googleplaces.address;
    this.gender = this.selectedGender;
    this.dateOfBirth = this.selectedDate;
    console.log(this.dateOfBirth);
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
    }else{
      this.address = this.googleplaces.address;
      var role : Role;
      role = Role.Patient
      var auths : Number[] = new Array();
      // console.log(this.address);
      this.patient = new Patient(this.name, this.surname, this.dateOfBirth, this.phone, this.email.toLowerCase(), this.password, this.gender, this.address, role, auths);
      // console.log(JSON.parse(JSON.stringify(this.patient)));
      if(this.passwordValid()){
        this.registrationService.registerPatient(this.patient).subscribe(
          res=>{
            this.registerForm.reset();
            this.googleplaces = null;
            alert('Success');
            this.router.navigate(['/login']);
          },
          error=>{
            alert("Fail")
          }
        )
      }else{
        alert('Passwords do not match');
      }
    }
    

  }
  passwordValid(){
    return this.password==this.confirmPassword;
  }
}
