import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-adminprofile',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  registerForm: FormGroup;
  name:String;
  surname:String;
  gender : String;
  address:String;
  phone:String;
  mail:String;
  constructor() { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({})
    this.name = "Pera";
    this.surname = "Peric";
    this.gender = "Male";
    this.address = "Bulevar Revolucije 69, Novi Sad, Srbija";
    this.phone = "19257124";
    this.mail = "pera.peric@uns.ac.rs";
  }
  registerPharmacy(){

  }
  registerDermatologist(){

  }
  registerAdmin(){

  }
  registerSupplier(){

  }
  operationsWithDrugs(){

  }
  respondToComplaints(){

  }
  defineLoyalty(){
    
  }
  adminLogout(){
    
  }
  editProfile(){
    
  }
  changePassword(){}
}
