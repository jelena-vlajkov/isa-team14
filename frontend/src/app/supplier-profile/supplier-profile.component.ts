import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Address } from '@app/model/address/address';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-profile',
  templateUrl: './supplier-profile.component.html',
  styleUrls: ['./supplier-profile.component.css']
})
export class SupplierProfileComponent implements OnInit {
  public supplier : Supplier;
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  name:String;
  surname:String;
  gender : String;
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
  public chgPass : PasswordChanger;
  public address1 :Address;
  editProfileForm: FormGroup;
  public firmName : String;
  public newCustomer : boolean = false;
  public firstTimeChanged:boolean;
  @ViewChild(GooglePlacesComponent) googleplaces;
  public home : boolean = true;
  constructor(private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.loadSupplier();
    this.editProfileForm = new FormGroup({});
    this.changePasswordForm = new FormGroup({});
    this.profileForm = new FormGroup({});
    this.password1 = "";
    this.password2 = "";
    this.oldpassword="";
    
  }

  supplierLogout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);

  }
  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
      });
  }
  respondToComplaints(){
    
  }
  cancelEdit(){
    this.profile = true; 
    this.edit = false;
    this.changePassword = false;
    this.home = false;

  }
  back(){
    this.loadSupplier();
    this.profile = false; 
    this.edit = false;
    this.changePassword = false;
    this.home = true;

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
    this.oldpassword = this.changePasswordForm.controls.oldpassword.value;
    if(this.password1 !== this.password2){
      console.log('New passwords do not match!');
    }
    else{
      this.chgPass = new PasswordChanger(Number(localStorage.getItem('userId')),this.oldpassword, this.password1, true);
      console.log(this.chgPass);
      this.supplierService.updatePassword(this.chgPass).subscribe(
        res=>{
          alert('Success');
          this.profile = true; 
          this.edit = false;
          this.changePassword = false;
          this.home = false;
          this.loadSupplier();
          this.firstTimeChanged=true;
        },
        error=>{
          alert("Fail")
        }
      )
    }
  }

  routeToProfile(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
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
  routeToOffers(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/supplier/offers']);
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
  routeToOrders(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/supplier/allOrders']);
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
  routetToStorage(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/supplier/storage']);
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
  routeToMeds(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/searchMedications']);
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
  routeToPharms(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/searchPharmacies']);
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
  routeToAllOffers(){
    this.firstTimeChanged = this.supplier.firstTimeChanged;
    console.log(this.firstTimeChanged);
    if(this.firstTimeChanged){
      this.router.navigate(['/supplier/allOffers']);
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
  editProfile(){
    this.edit = true;
    this.name = this.supplier.name;
    this.surname = this.supplier.surname;
    this.address = this.supplier.address;
    this.phone = this.supplier.phoneNumber;
    this.mail = this.supplier.email;
    this.dob = this.supplier.dateOfBirth;
    this.firmName = this.supplier.firmName;
    this.profile= false;
    this.changePassword = false;
    this.home = false;
    console.log(this.name);
    this.editProfileForm = new FormGroup({
      'name' : new FormControl(this.name, Validators.required),
      'surname' : new FormControl(this.surname, Validators.required),
      'telephone' : new FormControl(this.phone, Validators.required),
      'firmName' : new FormControl(this.firmName, Validators.required)
    });
  }
  confirmEdit(){
    var name = this.editProfileForm.controls.name.value;
    var surname = this.editProfileForm.controls.surname.value;
    var telephone =  this.editProfileForm.controls.telephone.value;
    var firmname = this.editProfileForm.controls.firmName.value;
    var mail = this.supplier.email;
    console.log(this.address1);
    console.log(this.googleplaces.status);
    
    console.log(this.address1);
    var editedSupplier = new Supplier(name, surname, this.supplier.dateOfBirth, telephone, mail, this.supplier.password, this.address1, this.supplier.role, this.supplier.authorities, firmname , this.supplier.firstTimeChanged);
    console.log(editedSupplier);
    this.supplierService.updateSupplier(editedSupplier).subscribe(
      res=>{
        alert('Success');
        this.profile = true; 
        this.edit = false;
        this.changePassword = false;
          this.home = false;
        this.loadSupplier();
      },
      error=>{
        alert("Fail")
      }
    )
  }

}
