import { Component } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '@app/service/employee/employee.service';
import {UserPreview} from '@app/model/pharmderm/userspreview'

import { AuthenticationService } from '../service/user/authentication.service'
@Component({
  selector: 'pharmacist-reports',
  templateUrl: './users.preview.component.html',
  styleUrls: ['./users.preview.component.css']
})
export class UsersPreview {
  public users : UserPreview[];
  public searchUsersForm : FormGroup;

  constructor(private authService : AuthenticationService, private router : Router, private employeeService : EmployeeService) {} 
  
  ngOnInit(): void {
      
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);

    }
    this.searchUsersForm = new FormGroup({
      'name' : new FormControl("", [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")])
    });

    this.employeeService.getAllUsers().subscribe(
      data => {
        this.users = data;
      }, error => {
        alert("Something went wrong!");
      }
    )
  }

  searchUsers() {
    let name = this.searchUsersForm.controls.name.value;
    console.log(name)
    this.employeeService.searchUsers(name).subscribe(
      data => {
        this.users = data;
      }, error => {
        alert("No match found!");
      }
    )
  }

  clearSearch() {
    this.employeeService.getAllUsers().subscribe(
      data => {
        this.users = data;
      }, error => {
        alert("Something went wrong!");
      }
    )
  }

  logout() {
    this.authService.logout();
  }
 
}