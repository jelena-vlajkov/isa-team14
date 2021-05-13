import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Router} from "@angular/router";
import { AuthenticationService } from '../service/user/authentication.service'

@Component({
  selector: 'app-pharmacy-patients',
  templateUrl: './pharmacist.patient.component.html',
  styleUrls: ['./pharmacist.patient.component.css']
})
export class PharmacistPatientsComponent implements OnInit {
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

  editProfileForm: FormGroup;

  constructor(private pharmacyAdminService:PharmacyAdminService,private router:Router, private authService: AuthenticationService ) { }

  ngOnInit(): void {
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);

    }
  
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";


    this.oldpassword = "peraBijeKera";

    this.pharmacyAdminService.getById(Number(localStorage.getItem('userId'))).subscribe(
      result => {
        this.name = result.name;
        this.surname = result.surname;
        if(result.gender==0)
          this.gender="Žensko";
        else if(result.gender==1)
          this.gender="Muško";
        else
          this.gender="Drugo";
        this.address = result.address.street+", "+result.address.city.name+", "+result.address.state.name;
        this.phone = result.phoneNumber;
        this.mail = result.email;    }
    );

  }
  logout() {
    this.authService.logout();
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
  cancelEdit(){
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
  }
  changePasswordFunction(){
    this.edit = false;
    this.profile = false;
    this.changePassword = true;

    this.changePasswordForm = new FormGroup({
      'oldpassword' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required)
    });
  }
  submitChangePassword(){
    this.password1 = this.changePasswordForm.value.newpassword;
    this.password2 = this.changePasswordForm.value.confirmpassword;
    if(this.password1 !== this.password2){
      console.log('NISU ISTI NE MOZE MATORI KONTAS BRT MOJ');
    }
  }
  addAdmin(){}
  editProfile(){
    this.edit = true;

    this.profile= false;
    this.changePassword = false;

    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(this.phone, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'gender': new FormControl(this.selectedGender, Validators.required)
    });
  }
}
