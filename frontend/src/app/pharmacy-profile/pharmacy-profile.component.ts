import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthenticatedUser} from "../model/users/authenticatedUser";

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  name:String;
  address:String;
  grade:String;
  about:String;
  currentUser:String;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;

  editProfileForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.currentUser=localStorage.getItem('user');
    console.log(this.currentUser);
    this.editProfileForm = new FormGroup({});

    this.name = "Apoteka Jankovic";
    this.address = "Narodnog Fronta 4, Novi Sad, Srbija";
    this.grade='5';
    this.about='traaaaalalalaaa';
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
