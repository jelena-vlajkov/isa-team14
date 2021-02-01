import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Proba } from './../service/Proba';
import {Authentication} from "../models/authentication";
import {AuthenticationService} from "../service/user";
import {Router} from "@angular/router";
import {Role} from "../models";

@Component({ templateUrl: 'login.component.html' })
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
        if(result.role == Role.Dermatologist){
          this.router.navigate(['/pharmacyAdmin-profile'])
        }
      });
  }
  signUp(){

  }
}
