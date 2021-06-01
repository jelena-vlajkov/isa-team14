import {Component, OnInit, ViewChild} from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Router} from "@angular/router";
import {AuthenticationService, UserService} from "@app/service/user";
import {AuthenticatedUser} from "@app/model/users/authenticatedUser";
import {Address} from "@app/model/address/address";
import {PharmacyAdmin} from "@app/model/users/pharmacyAdmin/pharmacyAdmin";
import {FirstTimePasswordChange} from "@app/model/users/firstTimePasswordChange";
import {EmployeeService} from "@app/service/employee/employee.service";
import {PasswordChanger} from "@app/model/users/passwordChanger";
import {EmployeePasswordChanger} from "@app/model/pharmderm/changepass";
import {GooglePlacesComponent} from "@app/google-places/google-places.component";

@Component({
  selector: 'app-pharmacy-admin-profile',
  templateUrl: './pharmacy-admin-profile.component.html',
  styleUrls: ['./pharmacy-admin-profile.component.css']
})
export class PharmacyAdminProfileComponent implements OnInit {
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  selectedGender : String;
  password1:String;
  password2:String;
  oldpassword:String;
  gender:String;
  @ViewChild(GooglePlacesComponent) googleplaces;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;
  currentUser: AuthenticatedUser;
  pharmacyAdmin:PharmacyAdmin;


  editProfileForm: FormGroup;

  constructor(private pharmacyAdminService:PharmacyAdminService,private router:Router
              ,private authenticationService:AuthenticationService,private employeeService:EmployeeService ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x)
  }

  ngOnInit(): void {
    console.log(localStorage.getItem('firstTimeChanged'));
    if ((localStorage.getItem('firstTimeChanged') === 'false')) {
      this.router.navigate(["/employee-welcome"]);

    }
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";
    this.oldpassword = "peraBijeKera";

    console.log(localStorage.getItem('userId'));
    this.pharmacyAdminService.getById(Number(localStorage.getItem('userId'))).subscribe(
      result => {
        this.pharmacyAdmin=result;
      }
    );

    if(this.pharmacyAdmin.gender.toString()=="MALE")
    {
      this.selectedGender="MALE";
      this.gender="Male";
    }
    else if(this.pharmacyAdmin.gender.toString()=="FEMALE")
    {
      this.selectedGender="FEMALE";
      this.gender="Female";
    }
    else
    {
      this.selectedGender="OTHER";
      this.gender="Other";
    }

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
      console.log('Passwords do not match.');
    }
    else{
        let changePass=new EmployeePasswordChanger(this.pharmacyAdmin.email,this.changePasswordForm.value.oldpassword,this.password2,null);
        this.employeeService.changeEmployeePassword(changePass).subscribe(result=>{
          console.log(result);
          this.profile=true;
          this.edit=false;
          this.changePassword=false;
        });
    }
  }



  editProfile(){
    this.edit = true;
    this.profile= false;
    this.changePassword = false;

    if(this.pharmacyAdmin.gender.toString()=="MALE")
    {
      this.selectedGender="MALE";
      this.gender="Male";
    }
    else if(this.pharmacyAdmin.gender.toString()=="FEMALE")
    {
      this.selectedGender="FEMALE";
      this.gender="Female";
    }
    else
    {
      this.selectedGender="OTHER";
      this.gender="Other";
    }


    this.editProfileForm = new FormGroup({

      'name' : new FormControl(this.pharmacyAdmin.name, Validators.required),
      'surname' : new FormControl(this.pharmacyAdmin.surname, Validators.required),
      'email' : new FormControl(this.pharmacyAdmin.email, [Validators.required,Validators.email]),
      'telephone' : new FormControl(this.pharmacyAdmin.phoneNumber, Validators.required),
      'gender': new FormControl(this.gender, Validators.required)
    });
  }

  editSubmited() {
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
    }else {
      this.pharmacyAdmin.address = this.googleplaces.address;
      this.pharmacyAdmin.name = this.editProfileForm.value.name;
      this.pharmacyAdmin.surname = this.editProfileForm.value.surname;
      this.pharmacyAdmin.dateOfBirth = this.editProfileForm.value.dateOfBirth;
      this.pharmacyAdmin.email = this.editProfileForm.value.email;
      this.pharmacyAdmin.phoneNumber = this.editProfileForm.value.telephone;
      this.pharmacyAdmin.gender = this.editProfileForm.value.gender;
      console.log(this.pharmacyAdmin);
      this.pharmacyAdminService.editPharmacyAdmin(this.pharmacyAdmin).subscribe(result => {
        this.edit = false;
        this.profile = true;
        this.changePassword = false;
      });
    }
  }
  checkLoggedInUser(){
    return this.authenticationService.getUserValue();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  saveAddress() {
    console.log(this.googleplaces.address);
  }
}
