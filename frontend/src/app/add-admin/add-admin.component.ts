import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { GooglePlacesComponent } from '../google-places/google-places.component';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { Role } from '@app/model/users/role';
import { Gender } from '@app/model/users/patient/gender';
import { Address } from '@app/model/address/address';
import { AuthenticationService } from '@app/service/user';
import { Router } from '@angular/router';

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
  gender : Gender;
  selectedGender;

  address : Address;
  phone:String;
  mail:String;
  password:String;
  confirmpassword:String;

  oldpassword:String;


  dateOfBirth : Date;
  public selectedDate;
  public sysAdmin : SystemAdmin;
  public places : String;
  addAdminForm: FormGroup;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private router: Router, private sysAdminRegistration : SysadminRegistrationService,private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
    this.addAdminForm = new FormGroup({
      'name' : new FormControl(null, Validators.required),
      'surname' : new FormControl(null, Validators.required),
      'mail' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required),
      'confirmpassword': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required)
    });
  }

  addAdmin(){
    this.name = this.addAdminForm.value.name;
    this.surname = this.addAdminForm.value.surname;
    this.phone = this.addAdminForm.value.telephone;
    this.mail = this.addAdminForm.value.mail;
    this.password = this.addAdminForm.value.password;
    this.confirmpassword = this.addAdminForm.value.confirmpassword;
    this.address = this.googleplaces.address;
    this.gender = this.selectedGender;
    this.dateOfBirth = this.selectedDate;
    var role : Role;
    role = Role.SysAdmin
    var auths : Number[] = new Array();
    console.log(this.password);
    this.sysAdmin = new SystemAdmin(null, this.name, this.surname, this.dateOfBirth, this.phone, this.mail, this.password, this.gender, this.address, role, auths, false);
    console.log(JSON.parse(JSON.stringify(this.sysAdmin)));

    if(this.passwordValid()){
      this.sysAdminRegistration.registerSysAdmin(this.sysAdmin).subscribe(
        res=>{
          this.addAdminForm.reset();
          this.googleplaces = null;
          alert('Success');
          this.router.navigate(['/admin']);

        },
        error=>{
          alert("Fail")
        }
      )
    }else{
      alert('Passwords do not match');
    }

  }
  passwordValid(){
    return this.password==this.confirmpassword;
  }
  registerDermatologist(){

  }
  adminLogout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);


  }
  routeToHome(){
    this.router.navigate(['/admin']);

  }
  respondToComplaints(){

  }
}
