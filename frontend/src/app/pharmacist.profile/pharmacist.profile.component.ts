import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '@app/model/users';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { AuthenticationService } from '../service/user/authentication.service';
import {UserService} from '../service/user/user.service';
import { DatePipe } from '@angular/common';
import { EmployeeService } from '@app/service/employee/employee.service';
import { UpdateEmployee } from '@app/model/pharmderm/UpdateEmployee';
import { Gender } from '@app/model/users/patient/gender';


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
    public dateOfBirth:string;
    public gender:string;
    minDateOfBirth : Date;
    maxDateOfBirth : Date;
    public selected:string;
    editProfileForm: FormGroup;
    genders:string[];
    @ViewChild(GooglePlacesComponent) googleplaces;

  
    constructor(private authService : AuthenticationService, private userService : UserService, private employeeService : EmployeeService) {

      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
     }
  
    ngOnInit(): void {
      this.genders = [];
      this.genders.push("Female");
      this.genders.push("Male");
      this.minDateOfBirth = new Date();
      this.maxDateOfBirth = new Date();
      this.minDateOfBirth.setFullYear((new Date()).getFullYear() - 100);
      this.maxDateOfBirth.setFullYear((new Date()).getFullYear() - 19);
      this.editProfileForm = new FormGroup({});
      this.userService.getLoggedInUser().subscribe(data =>
      {
        this.user = data;
        if (this.user.gender === "FEMALE") {
          this.gender = "Female";
        } else {
          this.gender = "Male";
        }
        const datepipe: DatePipe = new DatePipe('en-US')
        this.dateOfBirth = datepipe.transform(this.user.dateOfBirth, 'dd.MM.yyyy.')
      });
    }

    logout() {
      this.authService.logout();
    }
  
    editProfile(){
      this.isRequired = false;
      this.isEditMode = true;
      this.isNotEditMode = false;
      this.editProfileForm = new FormGroup({
        'name' : new FormControl(this.user.name, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        'surname' : new FormControl(this.user.surname, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        'phoneNumber' : new FormControl(this.user.phoneNumber, [Validators.required, Validators.pattern("^[0-9]*$")]),
        'gender': new FormControl(this.gender, Validators.required),
        'dob' : new FormControl(this.user.dateOfBirth, Validators.required)
      });
      
  
    }
    changePassword() {

    }
    cancelEdit() {
      this.editProfileForm = new FormGroup({});
      
      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;

    }
    saveProfile(){
        let employee = new UpdateEmployee();
        employee.name = this.editProfileForm.controls.name.value;
        employee.surname = this.editProfileForm.controls.surname.value;
        employee.dateOfBirth = this.editProfileForm.controls.dob.value;;
        employee.email = this.user.email;
        employee.gender = this.editProfileForm.controls.gender.value;;
        employee.phoneNumber = this.editProfileForm.controls.phoneNumber.value;
        
        this.employeeService.updateEmployee(employee).subscribe(
          res=>{
            alert("Successfully updated!")
            this.userService.getLoggedInUser().subscribe(data =>
              {
                this.user = data;
                if (this.user.gender === "FEMALE") {
                  this.gender = "Female";
                } else {
                  this.gender = "Male";
                }
                const datepipe: DatePipe = new DatePipe('en-US')
                this.dateOfBirth = datepipe.transform(this.user.dateOfBirth, 'dd.MM.yyyy.')
              });
            this.isRequired = true;
            this.isEditMode = false;
            this.isNotEditMode = true;
  
          },
          error=>{
            alert("Oh no, something went wrong!")
            
            this.isRequired = true;
            this.isEditMode = false;
            this.isNotEditMode = true;
          }
        )
      }
    
      }
   


