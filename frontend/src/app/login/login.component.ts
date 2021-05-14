import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Authentication} from "../model/users/authentication";
import {AuthenticationService} from "../service/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from "../model/users";
import { PatientService } from '@app/service/patient/patient.service';
import { RegistrationService } from '@app/service/registration/registration.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin: boolean;
  username: string;
  password: string;
  registerForm: FormGroup;
  credentials: Authentication;


  constructor(private activatedRoute: ActivatedRoute, private authService: AuthenticationService, private router: Router, private registrationService : RegistrationService) { 
    this.activatedRoute.queryParams.subscribe(params => {
      let token = params['token'];
      console.log(token); // Print the parameter to the console. 
      // this.registrationService.activatePatient(token).subscribe()
      if(token!==undefined){
        this.registrationService.activatePatient(token).subscribe(
          res=>{
            alert('Congradulation! Account has been activated');
          },
          error=>{
            alert("Sorry my friend, bad luck :/")
          }
        )
      }
      
  });
  }


  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'username' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, Validators.required)
    });
    this.registerForm = new FormGroup({});
  }
  signIn(){
    this.username = this.loginForm.controls.username.value;
    this.password = this.loginForm.controls.password.value;

    this.credentials = new Authentication(this.username.toLowerCase(), this.password);
    console.log(this.credentials);
    this.authService.login(this.credentials).subscribe(
      result => {
        localStorage.setItem('userId',String(result.id))
        localStorage.setItem('firstTimeChanged',String(result.firstTimeChanged))
        if(result.role == Role.PharmacyAdmin){
          this.router.navigate(['/pharmacyAdmin-profile'])
        }else if(result.role == Role.SysAdmin){
          this.router.navigate(['/admin'])
        } else if (result.role == Role.Dermatologist || result.role == Role.Pharmacist) {
          this.router.navigate(['/dashboard'])
        }
        else if(result.role == Role.Patient){
          this.router.navigate(['/'])
        }
        if(result.role == Role.Patient){
          this.router.navigate(['/patient/home'])
        }
        else if(result.role == Role.Supplier){
         this.router.navigate(['/supplier']);
        }
      },
      error=>{
        alert("Wrong username or password")
        this.router.navigate(['/login']);

      });
  }

}
