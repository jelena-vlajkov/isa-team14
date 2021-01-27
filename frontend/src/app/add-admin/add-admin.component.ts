import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { PlaceSuggestion } from '../autocomplete-geo/autocomplete-geo.component';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  name:String;
  surname:String;
  gender : String;
  selectedGender : String;

  address:String;
  phone:String;
  mail:String;
  password1:String;
  password2:String;

  oldpassword:String;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;
  
  addAdminForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'address' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'newpassword': new FormControl(null, Validators.required),
      'confirmpassword': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required)
    });
  }
  autocompleteChanged(value: PlaceSuggestion) {}

  add(){}
  addAdmin(){

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
}
