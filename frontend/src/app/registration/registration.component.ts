import { Component, Inject, OnInit } from '@angular/core';
import { Proba } from './../service/Proba';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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

  name : string;
  surname : string;
  phone : string;
  email : string;
  password : string;
  confirmPassword : string;
  gender : string;
 
  constructor(@Inject(Proba) private Proba : Proba) { }

  ngOnInit(): void {    
    this.registerForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'password1' : new FormControl(null, Validators.required),
      'password2' : new FormControl(null, Validators.required),
      'address' : new FormControl(null, Validators.required)
    });
    console.log("OM<GGSDGSD");
    this.Proba.proba().subscribe();


  }
  register(){
    this.name = this.registerForm.value.name;
    this.surname = this.registerForm.value.surname;
    this.phone = this.registerForm.value.telephone;
    this.email = this.registerForm.value.email;
    this.password = this.registerForm.value.password1;
    this.confirmPassword = this.registerForm.value.password2;
  }
}
