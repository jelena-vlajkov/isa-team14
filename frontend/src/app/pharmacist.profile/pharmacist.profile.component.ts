import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '@app/model/users';
import { AuthenticationService } from '../service/user/authentication.service';
import {UserService} from '../service/user/user.service';


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
    selector: 'app-pharmacistprofile',
    templateUrl: './pharmacist.profile.component.html',
    styleUrls: ['./pharmacist.profile.component.css']
  })

export class PharmacistProfileComponent implements OnInit {
  
    public user : User;
    public isRequired:boolean = true;
    public isEditMode:boolean = false;
    public isNotEditMode:boolean = true;
  
  
    editProfileForm: FormGroup;
  
    constructor(private authService : AuthenticationService, private userService : UserService) {

      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
     }
  
    ngOnInit(): void {
      this.editProfileForm = new FormGroup({});
      this.userService.getLoggedInUser().subscribe(data =>
      {
        this.user = data;
      });
    }

    logout() {
      this.authService.logout();
    }
  
    editProfile(){
      this.isRequired = false;
      this.isEditMode = true;
      this.isNotEditMode = false;
      
  
    }
    
    saveProfile(){
        this.isRequired = true;
        this.isEditMode = false;
        this.isNotEditMode = true;
        
    
      }
  
}

