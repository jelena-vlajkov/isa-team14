import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-pharmacy',
  templateUrl: './register-pharmacy.component.html',
  styleUrls: ['./register-pharmacy.component.css']
})
export class RegisterPharmacyComponent implements OnInit {
  registerPharmacy: FormGroup;
  addAdminForm : FormGroup;
  selectedGender : String;
  public adminAdded : boolean = false;
  constructor() { }

  ngOnInit(): void {
    this.registerPharmacy = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'address': new FormControl(null, Validators.required),
    });
    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'address' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required)
    });
  }
  addPharmacy(){}
  registerDermatologist(){

  }
  registerAdmin(){
    //TODO DODATI FUNKCIJE
    return true;
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
  addAdmin(){
    if(this.registerAdmin() == true){
      this.adminAdded = true;
    }
  }
}
