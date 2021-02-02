import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from './../model/address/address';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';

@Component({
  selector: 'app-register-pharmacyadmin',
  templateUrl: './register-pharmacyadmin.component.html',
  styleUrls: ['./register-pharmacyadmin.component.css']
})
export class RegisterPharmacyadminComponent implements OnInit {
  registerPharmacy: FormGroup;
  addAdminForm : FormGroup;
  selectedGender : String;

  public pharmacy : Pharmacy;
  public pharmacies : Pharmacy[]= new Array();
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private pharmacyService : PharmacyService) { }

  ngOnInit(): void {

    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required),
      'pharmacy' : new FormControl(null, Validators.required)
    });
    this.loadAllPharmacies();
  }
  addPharmacy(){

  }

  registerDermatologist(){

  }
  pharmacyValid(){
    if(this.googleplaces.address===undefined){
      return false;
    }
    return true;
  }
  adminValid(){

  }
  registerAdmin(){
    //TODO DODATI FUNKCIJE
    return true;
  }
  loadAllPharmacies() {
    this.pharmacyService.findAllPharmacies().subscribe(data => 
      {
        this.pharmacies = data
      });
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

  }
}
