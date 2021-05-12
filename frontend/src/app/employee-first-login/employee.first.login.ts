import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService, UserService} from "../service/user";
import {IngredientService} from "../service/medication/ingredients.service"
import {ReportsService} from "../service/reports/reports.service"
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { User } from '@app/model/users';

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

  constructor(private authService: AuthenticationService, private userService : UserService, private router: Router) { }

  ngOnInit(): void {
  
    this.userService.getLoggedInUser().subscribe(data =>
        {
          this.user = data;
        });

    if (this.user.firstTimePassword) {
      this.router.navigate(["/dashboard"]);
    }

    this.changePassForm = new FormGroup({
      'newpass' : new FormControl("", [Validators.required,Validators.minLength(8)]),
      'repnewpass' : new FormControl("", [Validators.required,Validators.minLength(8)])
    });


  }

  logout() {
    this.authService.logout();
  }

}

