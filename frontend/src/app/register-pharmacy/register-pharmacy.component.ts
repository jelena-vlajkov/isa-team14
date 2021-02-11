import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from './../model/address/address';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/service/user';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';

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
  public sysAdmin;

  constructor(private systemAdminService : SysadminRegistrationService ,private authenticationService:AuthenticationService, private pharmacyService : PharmacyService, private router:Router) { }

  ngOnInit(): void {
    this.loadSystemAdmin();
    this.registerPharmacy = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'description' : new FormControl(null, Validators.required)
    });
  }
  addPharmacy(){
    this.pharmacy_description = this.registerPharmacy.value.description;
    this.pharmacy_name = this.registerPharmacy.value.name;
    
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
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

  registerSupplier(){

  }
  operationsWithDrugs(){

  }
  respondToComplaints(){

  }
  defineLoyalty(){

  }

  editProfile(){

  }
  loadSystemAdmin(){
    this.systemAdminService.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      {
        this.sysAdmin = new SystemAdmin(Number(localStorage.getItem('userId')), data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities, data.firstTimeChanged);
        if(!this.sysAdmin.firstTimeChanged){
          this.router.navigate(['/admin']);
        }
      });

  }
  adminLogout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
