import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthenticatedUser} from "../model/users/authenticatedUser";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Role} from "@app/models";
import {Address} from "@app/model/address/address";

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  name:String;
  address:String;
  grade:number;
  about:String;
  currentUserId:String;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;

  editProfileForm: FormGroup;

  constructor(private pharmacyAdminService:PharmacyAdminService) { }

  ngOnInit(): void {
    this.currentUserId=localStorage.getItem('userId');
    console.log(this.currentUserId);
    this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
      result => {
          this.name=result.name;
          this.grade=result.average_grade;
          this.about=result.description;
      });

    this.editProfileForm = new FormGroup({});

  }


  cancelEdit(){
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
  }

  addAdmin(){}
  editProfile(){
    this.edit = true;

    this.profile= false;
    this.changePassword = false;

    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.name, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'grade': new FormControl(this.grade, Validators.required)
    });
  }

}
