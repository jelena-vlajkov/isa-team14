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
import { EmployeePasswordChanger } from '@app/model/pharmderm/changepass';
import { Router } from '@angular/router';


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
    public changePassMode:boolean = false;
    public isNotEditMode:boolean = true;
    public dateOfBirth:string;
    public gender:string;
    minDateOfBirth : Date;
    maxDateOfBirth : Date;
    changePassForm : FormGroup;
    public selected:string;
    editProfileForm: FormGroup;
    genders:string[];
    @ViewChild(GooglePlacesComponent) googleplaces;

  
    constructor(private authService : AuthenticationService, private userService : UserService, private employeeService : EmployeeService, private router : Router) {

      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
      this.changePassMode = false;
     }
  
    ngOnInit(): void {
      
      if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
        this.router.navigate(["/employee-welcome"]);
  
      }
    
      this.genders = [];
      this.genders.push("Female");
      this.genders.push("Male");
      this.minDateOfBirth = new Date();
      this.maxDateOfBirth = new Date();
      this.minDateOfBirth.setFullYear((new Date()).getFullYear() - 100);
      this.maxDateOfBirth.setFullYear((new Date()).getFullYear() - 19);
      this.editProfileForm = new FormGroup({});
      this.changePassForm = new FormGroup({});
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
    saveNewPass() {
      let newPass = this.changePassForm.controls.newpass.value;
      let repNewPass = this.changePassForm.controls.repnewpass.value;
      if (newPass !== repNewPass) {
        alert("Passwords do not match!");
      } else {
        let changePass = new EmployeePasswordChanger(this.user.email,
          this.changePassForm.controls.oldpass.value,
          this.changePassForm.controls.newpass.value,
          true
          );


          
        this.employeeService.changeEmployeePassword(changePass).subscribe(
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
            this.changePassMode = false;

          },
          error=>{
            alert(error)
            
            this.isRequired = true;
            this.isEditMode = false;
            this.isNotEditMode = false;
            this.changePassMode = true;
          }
        )
      }

      

    }
    editProfile(){
      this.isRequired = false;
      this.isEditMode = true;
      this.isNotEditMode = false;
      this.changePassMode = false;
      this.editProfileForm = new FormGroup({
        'name' : new FormControl(this.user.name, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        'surname' : new FormControl(this.user.surname, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        'phoneNumber' : new FormControl(this.user.phoneNumber, [Validators.required, Validators.pattern("^[0-9]*$")]),
        'gender': new FormControl(this.gender, Validators.required),
        'dob' : new FormControl(this.user.dateOfBirth, Validators.required)
      });
      
  
    }
    changePassword() {

      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = false;
      this.changePassMode = true;
      this.changePassForm = new FormGroup({
        'oldpass' : new FormControl("", [Validators.required,Validators.minLength(5)]),
        'newpass' : new FormControl("", [Validators.required,Validators.minLength(8)]),
        'repnewpass' : new FormControl("", [Validators.required,Validators.minLength(8)])
      });

      
    }
    cancelEdit() {
      this.editProfileForm = new FormGroup({});
      
      this.isRequired = true;
      this.isEditMode = false;
      this.isNotEditMode = true;
      this.changePassMode = false;

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
            this.changePassMode = false;
  
          },
          error=>{
            alert("Oh no, something went wrong!")
            
            this.isRequired = true;
            this.isEditMode = false;
            this.isNotEditMode = true;
            this.changePassMode = false;
          }
        )
      }
    
      }
   


