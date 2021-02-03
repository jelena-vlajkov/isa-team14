import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "@app/service/user";
import {AuthenticatedUser} from "@app/models/authenticatedUser";
import {Address} from "@app/model/address/address";
import {PharmacyAdmin} from "@app/model/pharmacyAdmin/pharmacyAdmin";

@Component({
  selector: 'app-pharmacy-admin-profile',
  templateUrl: './pharmacy-admin-profile.component.html',
  styleUrls: ['./pharmacy-admin-profile.component.css']
})
export class PharmacyAdminProfileComponent implements OnInit {
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  name:String;
  surname:String;
  gender : String;
  selectedGender : String;

  address:String;
  telephone:String;
  mail:String;
  password1:String;
  password2:String;


  oldpassword:String;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;
  currentUser: AuthenticatedUser;
  pharmacyAdmin:PharmacyAdmin;


  editProfileForm: FormGroup;

  constructor(private pharmacyAdminService:PharmacyAdminService,private router:Router
              ,private authenticationService:AuthenticationService ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x)
  }

  ngOnInit(): void {
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
        if(result.gender.toString()=="FEMALE") { this.gender="Žensko"; }
        else if(result.gender.toString()=="MALE") { this.gender="Muško"; }
        else { this.gender="Drugo"; }
        this.address = result.address.street+", "+result.address.city.name+", "+result.address.state.name;
        this.telephone = result.phoneNumber;
        this.mail = result.email;
      }
    );

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
    this.authenticationService.logout();
    this.router.navigate(['/login']);
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
    if(this.gender=="Muško")
    {
      this.selectedGender="Male";
    }
    else if(this.gender=="Žensko")
    {
      this.selectedGender="Female";
    }
    else
    {
      this.selectedGender="Other";
    }

    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'email' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(this.telephone, Validators.required),
      'address' : new FormControl(this.address, Validators.required),
      'gender': new FormControl(this.selectedGender, Validators.required)
    });
  }

  editSubmited() {
    this.name=this.editProfileForm.value.name;
    this.surname=this.editProfileForm.value.surname;
    this.gender=this.editProfileForm.value.selectedGender;
    this.telephone=this.editProfileForm.value.telephone;
    //this.pharmacyAdmin=new PharmacyAdmin(this.name,this.surname,this.phoneNumber,this.telephone)


  }
}
