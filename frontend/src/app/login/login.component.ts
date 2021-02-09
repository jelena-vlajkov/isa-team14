import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Authentication} from "../model/users/authentication";
import {AuthenticationService} from "../service/user";
import {Router} from "@angular/router";
import {Role} from "../model/users";


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


  constructor(private authService: AuthenticationService, private router: Router) { }


  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'username' : new FormControl(null, Validators.required),
      'password' : new FormControl(null, Validators.required)
    });
    this.registerForm = new FormGroup({});
  }
  signIn(){
    this.username = this.loginForm.controls.username.value;
    this.password = this.loginForm.controls.password.value;

    this.credentials = new Authentication(this.username, this.password);
    console.log(this.credentials);
    this.authService.login(this.credentials).subscribe(
      result => {
        localStorage.setItem('userId',String(result.id))
        if(result.role == Role.PharmacyAdmin){
          this.router.navigate(['/pharmacyAdmin-profile'])
        }else if(result.role == Role.SysAdmin){
          this.router.navigate(['/admin'])
        } else if (result.role == Role.Dermatologist || result.role == Role.Pharmacist) {
          this.router.navigate(['/pharmacist'])
        }
        else if(result.role == Role.Patient){
          this.router.navigate(['/'])
        }
        else if(result.role == Role.Supplier){
         this.router.navigate(['/supplier']);
        }
      });
  }

}
