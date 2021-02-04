import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Gender } from '@app/model/users/patient/gender';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { AuthenticationService } from '@app/service/user';
import { Address } from './../model/address/address';

@Component({
  selector: 'app-adminprofile',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public sysAdmin : SystemAdmin;
  public sysAdminBackup: SystemAdmin;
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  name:String;
  surname:String;
  gender : String;
  selectedGender : Gender;
  dob: Date;
  address:Address;
  phone:String;
  mail:String;
  password1:String;
  password2:String;
  dateString: String;
  oldpassword:String;
  editDate : FormControl;
  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;

  public address1 :Address;
  editProfileForm: FormGroup;
  @ViewChild(GooglePlacesComponent) googleplaces;

  constructor(private authenticationService : AuthenticationService, private systemAdminService : SysadminRegistrationService) { }

  ngOnInit(): void {
    this.loadSystemAdmin();
    
      
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";

    this.oldpassword = "peraBijeKera";


    this.editDate = new FormControl(this.dob.toISOString());
    
  }

  loadSystemAdmin(){
    this.systemAdminService.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      {
        this.sysAdmin = new SystemAdmin(data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities);
        this.sysAdminBackup = new SystemAdmin(data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities);
        this.address1 = data.sysAddress;
      });
  }
  respondToComplaints(){

  }

  adminLogout(){
    this.authenticationService.logout();
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
    this.name = this.sysAdmin.sysName;
    this.surname = this.sysAdmin.sysSurname;
    this.selectedGender = this.sysAdmin.sysGender;
    this.address = this.sysAdmin.sysAddress;
    this.phone = this.sysAdmin.sysPhoneNumber;
    this.mail = this.sysAdmin.sysEmail;
    this.dob = this.sysAdmin.sysDateOfBirth;
    
    this.profile= false;
    this.changePassword = false;
    console.log(this.name);
    this.editProfileForm = new FormGroup({
      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'telephone' : new FormControl(this.phone, Validators.required),
      'gender': new FormControl(this.selectedGender, Validators.required),
      'dob' : new FormControl(this.dob, Validators.required)
    });
    

  }
  confirmEdit(){
    var name = this.editProfileForm.controls.name.value;
    var surname = this.editProfileForm.controls.surname.value;
    var gender = this.selectedGender;
    var telephone =  this.editProfileForm.controls.telephone.value;
    var dob = this.editProfileForm.controls.dob.value;
    var mail = this.sysAdmin.sysEmail;
    // console.log(telephone);
    if(this.googleplaces!==undefined){
      this.address1 = this.googleplaces.address;
    }
    console.log(this.address1);
    var editedAdmin = new SystemAdmin(name, surname, dob, telephone, mail, this.sysAdmin.sysPassword, gender, this.address1, this.sysAdmin.sysRole, this.sysAdmin.sysAuthorities);
    console.log(editedAdmin);
    this.systemAdminService.updateSysAdmin(editedAdmin).subscribe(
      res=>{
        alert('Success');
        this.profile = true; 
        this.edit = false;
        this.changePassword = false;
      },
      error=>{
        alert("Fail")
      }
    )
  }
}
