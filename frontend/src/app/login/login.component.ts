import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Proba } from './../service/Proba';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [Proba]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  invalidLogin: boolean;
  username: string;
  password: string;
  registerForm: FormGroup;

  constructor(private Proba : Proba) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'email' : new FormControl(null, Validators.required),
      'password' : new FormControl(null, Validators.required)
    });
    this.registerForm = new FormGroup({});
    this.Proba.proba();
  }
  signIn(){
    this.username = this.loginForm.value.username;
    this.password = this.loginForm.value.password;
    console.log(this.username + this.password)

    // this.credentials = new Authentication(this.username, this.password);
    // this.authService.login(this.credentials).subscribe(
    //   result => {
    //     if(result.role == Role.Admin){
    //       this.router.navigate(['/allFeedback'])
    //     }
    //     else if(result.role == Role.Patient) {
    //       this.router.navigate(['/home']);
    //     }
    //   },
    //   error => {
    //     this.toastr.error("Invalid username or password");
    //   }
    // );
  }
  signUp(){
    
  }
}
