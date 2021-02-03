import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from './../model/address/address';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-pharmacy',
  templateUrl: './register-pharmacy.component.html',
  styleUrls: ['./register-pharmacy.component.css']
})
export class RegisterPharmacyComponent implements OnInit {
  registerPharmacy: FormGroup;
  pharmacy_name : String;
  pharmacy_description : String;
  pharmacy_location : Address;
  pharmacy_location_input: String;
  public pharmacy : Pharmacy;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private pharmacyService : PharmacyService, private router:Router) { }

  ngOnInit(): void {
    this.registerPharmacy = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'description' : new FormControl(null, Validators.required)
    });
    // this.addAdminForm = new FormGroup({
    //   'name' : new FormControl(null, Validators.required),
    //   'surname' : new FormControl(null, Validators.required),
    //   'gender': new FormControl(null, Validators.required),
    //   'dob' : new FormControl(null, Validators.required),
    //   'telephone' : new FormControl(null, Validators.required),
    //   'mail' : new FormControl(null, Validators.required),
    //   'newpassword' : new FormControl(null, Validators.required),
    //   'confirmpassword' : new FormControl(null, Validators.required),
    //   'pharmacy' : new FormControl(null, Validators.required)
    // });
  }
  addPharmacy(){
    this.pharmacy_description = this.registerPharmacy.value.description;
    this.pharmacy_name = this.registerPharmacy.value.name;
    
    if(this.googleplaces==undefined){
      alert("Please fill address.");
    }else{
      this.pharmacy_location = this.googleplaces.address;
      this.pharmacy_location_input = this.googleplaces.autocompleteInput;
  
      this.pharmacy = new Pharmacy(null, this.pharmacy_name, this.pharmacy_description, this.pharmacy_location, 0.0);

      this.pharmacyService.registerPharmacy(this.pharmacy).subscribe(
        res=>{
          this.registerPharmacy.reset();
          this.googleplaces = null;
          alert('Success');
          this.router.navigate(['/admin']);
        },
        error=>{
          alert("Fail");
        });
    }
  }

  registerDermatologist(){

  }
  // pharmacyValid(){
  //    if(this.googleplaces.address===undefined){
  //      return false;
  //    }
  //    return true;
  // }
  adminValid(){
    // if(this.admin_location===undefined){
    //   return false;
    // }
    // return true;
  }
  // registerAdmin(){
  //   //TODO DODATI FUNKCIJE
  //   return true;
  // }
  // loadAllPharmacies() {
  //   this.pharmacyService.findAllPharmacies().subscribe(data => 
  //     {
  //       this.pharmacies = data
  //     });
  // }
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
  // addAdmin(){
  //   if(this.registerAdmin() == true){
  //     this.adminAdded = true;
  //   }
  // }
}
