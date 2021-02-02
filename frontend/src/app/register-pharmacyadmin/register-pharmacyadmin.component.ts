import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from './../model/address/address';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { Gender } from '@app/model/patient/gender';
import { PharmacyAdmin } from '@app/model/pharmacyAdmin/pharmacyAdmin';
import { Role } from '@app/model/users/role';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-pharmacyadmin',
  templateUrl: './register-pharmacyadmin.component.html',
  styleUrls: ['./register-pharmacyadmin.component.css']
})
export class RegisterPharmacyadminComponent implements OnInit {
  addAdminForm : FormGroup;
  selectedGender;


  address : Address;
  name : string;
  surname : string;
  phone : string;
  email : string;
  password : string;
  confirmPassword : string;
  gender : Gender;
  selectedDate;
  dateOfBirth : Date;

  admin_location : Address;
  admin_location_input: String;
  public pharmacy : Pharmacy;
  public pharmacies : Pharmacy[]= new Array();
  public pharmacyAdmin : PharmacyAdmin;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private pharmacyService : PharmacyService, private router:Router) { }

  ngOnInit(): void {

    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'password' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required),
      'pharmacy' : new FormControl(null, Validators.required)
    });
    this.loadAllPharmacies();
  }
 

  registerDermatologist(){

  }
  adminLogout(){}
  registerAdmin(){
    this.name = this.addAdminForm.value.name;
    this.surname = this.addAdminForm.value.surname;
    this.phone = this.addAdminForm.value.telephone;
    this.email = this.addAdminForm.value.mail;
    this.password = this.addAdminForm.value.password;
    this.confirmPassword = this.addAdminForm.value.confirmpassword;
    if(this.googleplaces===undefined){
      alert("Please fill the address!");
    }else{
      this.address = this.googleplaces.address;
      this.gender = this.selectedGender;
      this.dateOfBirth = this.selectedDate;
      this.pharmacy = this.addAdminForm.value.pharmacy;
      console.log(this.pharmacy.address.coordinates);
      var role : Role;
      role = Role.PharmacyAdmin;
      var auths : Number[] = new Array();
      if(this.password === this.confirmPassword){
        this.pharmacyAdmin = new PharmacyAdmin(this.name, this.surname, this.dateOfBirth, this.phone, this.email, this.password, this.gender, this.address, role, auths, this.pharmacy);

        this.pharmacyService.registerPharmacyAdmin(this.pharmacyAdmin).subscribe(
          res=>{
            this.addAdminForm.reset();
            this.googleplaces = null;
            alert('Success');
            this.router.navigate(['/admin']);
          },
          error=>{
            alert("Fail");
          });
      }else{
        alert('Passwords do not match!');
      }
      
    }

    
  }
  loadAllPharmacies() {
    this.pharmacyService.findAllPharmacies().subscribe(data => 
      {
        this.pharmacies = data
      });
  }

  respondToComplaints(){

  }

}
