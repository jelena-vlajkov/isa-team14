import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { Gender } from '@app/model/users/patient/gender';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { AuthenticationService } from '@app/service/user';
import { ChangePasswordService } from '@app/service/user/change-password.service';
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
  public profile:boolean = false;
  public edit:boolean = false;
  public changePassword:boolean = false;
  public home:boolean = true;

  public chgPass : PasswordChanger;
  public address1 :Address;
  editProfileForm: FormGroup;
  public newCustomer : boolean = false;
  @ViewChild(GooglePlacesComponent) googleplaces;
  public firstTimeChanged : boolean;
  constructor(private authenticationService : AuthenticationService, private systemAdminService : SysadminRegistrationService, private router: Router) { }

  ngOnInit(): void {
    this.loadSystemAdmin();
    
      
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";
    this.oldpassword="";    
  }

  loadSystemAdmin(){
    this.systemAdminService.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      {
        this.sysAdmin = new SystemAdmin(Number(localStorage.getItem('userId')), data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities, data.firstTimeChanged);
        this.address1 = data.sysAddress;
      });

  }

  back(){
    this.loadSystemAdmin();
    this.profile = false; 
    this.edit = false;
    this.changePassword = false;
    this.home = true;

  }
  routeToProfile(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
        this.profile=true;
        this.home = false;
        this.edit  = false;
        this.changePassword = false;
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
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
    this.home = false;
  }
  changePasswordFunction(){
    this.edit = false;
    this.profile = false;
    this.changePassword = true;
    this.home = false;

    this.changePasswordForm = new FormGroup({
      'oldpassword' : new FormControl(null, Validators.required),
      'newpassword' : new FormControl(null, Validators.required),
      'confirmpassword' : new FormControl(null, Validators.required)
    });

  }
  submitChangePassword(){
    this.password1 = this.changePasswordForm.value.newpassword;
    this.password2 = this.changePasswordForm.value.confirmpassword;
    this.oldpassword = this.changePasswordForm.controls.oldpassword.value;
    if(this.password1 !== this.password2){
      console.log('New passwords do not match!');
    }
    else{
      this.chgPass = new PasswordChanger(Number(localStorage.getItem('userId')),this.oldpassword, this.password1, true);
      console.log(this.chgPass);
      this.systemAdminService.updatePassword(this.chgPass).subscribe(
        res=>{
          alert('Success');
          this.profile = true; 
          this.edit = false;
          this.changePassword = false;
          this.loadSystemAdmin();
          this.home = false;

        },
        error=>{
          alert("Fail")
        }
      )
    }
  }
  addAdmin(){}
  editProfile(){
    this.edit = true;
    this.home = false;

    this.name = this.sysAdmin.sysName;
    this.surname = this.sysAdmin.sysSurname;
    this.selectedGender = this.sysAdmin.sysGender;
    this.address = this.sysAdmin.sysAddress;
    this.phone = this.sysAdmin.sysPhoneNumber;
    this.mail = this.sysAdmin.sysEmail;
    this.dob = this.sysAdmin.sysDateOfBirth;
    
    this.profile= false;
    this.changePassword = false;
    console.log(this.sysAdmin.sysAddress);
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
    console.log(this.address1);
    console.log(this.googleplaces===null);
    
    console.log(this.address1);
    var editedAdmin = new SystemAdmin(Number(localStorage.getItem('userId')), name, surname, dob, telephone, mail, this.sysAdmin.sysPassword, gender, this.address1, this.sysAdmin.sysRole, this.sysAdmin.sysAuthorities, this.sysAdmin.firstTimeChanged);
    console.log(editedAdmin);
    this.systemAdminService.updateSysAdmin(editedAdmin).subscribe(
      res=>{
        alert('Success');
        this.profile = true; 
        this.edit = false;
        this.changePassword = false;
        this.loadSystemAdmin();
        this.home = false;

      },
      error=>{
        alert("Fail")
      }
    )
  }
  routeToDrugReg(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/addDrug']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  routeToSysAdminReg(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/addAdmin']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  routeToHome(){
    if(this.changePassword){
      this.changePassword=false;
      this.home = true;
    }
    
  }
  routeToPharmacyReg(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/registerPharmacy']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  respondToComplaints(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/answerComplaints']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  routeToPhAdmin(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/registerPharmacyAdmin']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  routeToDermatologistReg(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/registerDermatologist']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  routeToSupplierReg(){
    this.firstTimeChanged = this.sysAdmin.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/admin/registerSupplier']);
    }
    else
    {
      this.changePasswordFunction();
      this.profile=false;
      this.home = false;
      this.edit  = false;
      this.changePassword = true; 
    }
  }
  
}
