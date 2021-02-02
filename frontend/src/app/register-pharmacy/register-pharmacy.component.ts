import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from './../model/address/address';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyRegistrationService } from '@app/service/pharmacy-registration/pharmacy-registration.service';

@Component({
  selector: 'app-register-pharmacy',
  templateUrl: './register-pharmacy.component.html',
  styleUrls: ['./register-pharmacy.component.css']
})
export class RegisterPharmacyComponent implements OnInit {
  registerPharmacy: FormGroup;
  addAdminForm : FormGroup;
  selectedGender : String;
  public allFilled : boolean = false;
  public adminAdded : boolean = false;
  public pharmacyValid : boolean = false;

  pharmacy_name : String;
  pharmacy_description : String;
  pharmacy_location : Address;
  pharmacy_location_input: String;
  public pharmacy : Pharmacy;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private registerPharmacyService : PharmacyRegistrationService) { }

  ngOnInit(): void {
    this.registerPharmacy = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'description' : new FormControl(null, Validators.required)
    });
    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required)
    });
  }
  addPharmacy(){
    this.pharmacy_description = this.registerPharmacy.value.description;
    this.pharmacy_name = this.registerPharmacy.value.name;
    this.pharmacy_location = this.googleplaces.address;
    this.pharmacy_location_input = this.googleplaces.autocompleteInput;

    if(this.pharmacy_location===undefined){
      alert("Please fill address.");
    }else{
      this.pharmacy = new Pharmacy(this.pharmacy_name, this.pharmacy_description, this.pharmacy_location, 0.0);

      this.registerPharmacyService.registerPharmacy(this.pharmacy).subscribe(
        res=>{
          this.registerPharmacy.reset();
          this.googleplaces = null;
          alert('Success');
        },
        error=>{
          alert("Fail");
        });
    }
  }

  checkValidData(){
    this.pharmacy_description = this.registerPharmacy.value.description;
    this.pharmacy_name = this.registerPharmacy.value.name;
    this.pharmacy_location = this.googleplaces.address;
    this.pharmacy_location_input = this.googleplaces.autocompleteInput;

    if(this.pharmacy_description.toString().trim().length!=0 && this.pharmacy_name.trim.length!=0 && this.pharmacy_location.toString().length!=0 && this.pharmacy_location_input.toString().trim().length !=0){
      this.pharmacyValid = true;
    }else{
      this.pharmacyValid = false;
    }

    return this.pharmacyValid;
  }
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
