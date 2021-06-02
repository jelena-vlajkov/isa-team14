import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService, UserService} from "../service/user";
import {IngredientService} from "../service/medication/ingredients.service"
import {ReportsService} from "../service/reports/reports.service"
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { User } from '@app/model/users';
import { FirstTimePasswordChange } from '@app/model/users/firstTimePasswordChange';
import { EmployeeService } from '@app/service/employee/employee.service';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: '/pharmacist/profile', title: 'Profile',  icon: 'person', class: '' },
    { path: '/pharmacist/calendar', title: 'Calendar',  icon: 'calendar', class: ''}
];

@Component({
    selector: 'app-pharmacist',
    templateUrl: './employee.first.login.html',
    styleUrls: ['./employee.first.login.css']
  })

export class WelcomeComponent implements OnInit {

  menuItems: any[];
  changePassForm : FormGroup;
  editProfileForm: FormGroup;
  user : User;

  constructor(private authService: AuthenticationService, private userService : UserService, private router: Router, private employeeService : EmployeeService) { }

  ngOnInit(): void {
    if (localStorage.getItem('firstTimeChanged') === 'true') {
      this.router.navigate(["/dashboard"]);

    }

    this.changePassForm = new FormGroup({
      'newpass' : new FormControl("", [Validators.required,Validators.minLength(8)]),
      'repnewpass' : new FormControl("", [Validators.required,Validators.minLength(8)])
    });


  }

  changePasswordForTheFirstTime() {
    let newPass = this.changePassForm.controls.newpass.value;
    let repNewPass = this.changePassForm.controls.repnewpass.value;
    if (newPass !== repNewPass) {
      alert("Passwords do not match!");
    } else {

      this.userService.getLoggedInUser().subscribe(data =>
        {
          this.user = data;

          let changePass = new FirstTimePasswordChange(this.user.email,
            newPass,
            repNewPass,
            true
            );



          this.employeeService.firstTimePasswordChange(changePass).subscribe(
            res=>{
              alert("Welcome!")
              localStorage.setItem('firstTimeChanged', "true");
              console.log(this.user.role);
              if(this.user.role === "PharmacyAdmin"){
                this.router.navigate(["/pharmacyAdmin-profile"]);
              }
              else{
                this.router.navigate(["/dashboard"]);
              }

            },
            error=>{
              alert(error);
            }
          )
        })
      }
    }

  logout() {
    this.authService.logout();
  }
}

