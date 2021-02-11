import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableModule } from '@angular/material/table'; 
import { IngredientService } from '../service/medication/ingredients.service';
import { Ingredient } from '../model/medications/ingredient';
import { Medication } from '@app/model/medications/medication';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Supplier } from '@app/model/users/supplier/supplier';
import { Address } from '@app/model/address/address';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Role } from '@app/model/users';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/service/user';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';

@Component({
  selector: 'app-register-supplier',
  templateUrl: './register-supplier.component.html',
  styleUrls: ['./register-supplier.component.css']
})
export class RegisterSupplierComponent implements OnInit {
  addSupplier : FormGroup;
  public supplier : Supplier;
  name : String;
  surname : String;
  telephone : String;
  email : String;
  password : String;
  confirmpassword : String;
  headquarters : Address;
  selectedDate;
  selectedGender;
  @ViewChild(GooglePlacesComponent) googleplaces;
  firmName: String;
  public sysAdmin;
  constructor(private systemAdminService : SysadminRegistrationService,private authenticationService : AuthenticationService , private supplierService : SupplierService, private router : Router) { }

  ngOnInit(): void {
    this.sysAdmin;
    this.addSupplier = new FormGroup({
      'name' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'mail' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null,[Validators.required,Validators.minLength(8)]),
      'confirmpassword' : new FormControl(null, [Validators.required,Validators.minLength(8)]),
      'fname' : new FormControl(null,  Validators.required)

    });

  }
  registerSupplier(){
    
    this.name = this.addSupplier.value.name;
    this.surname = this.addSupplier.value.surname;
    this.telephone = this.addSupplier.value.telephone;
    this.email = this.addSupplier.value.mail;
    this.password = this.addSupplier.value.password;
    this.confirmpassword = this.addSupplier.value.confirmpassword;
    this.firmName = this.addSupplier.value.fname;
    if(this.googleplaces.address===undefined){
      alert("Please fill the address!");
    }else{
      this.headquarters = this.googleplaces.address;
      var role : Role;
      role = Role.Supplier;
      var auths : Number[] = new Array();
      if(this.password === this.confirmpassword){
        this.supplier = new Supplier(this.name, this.surname, new Date(),this.telephone, this.email, this.password, this.headquarters, role, auths, this.firmName, false);
        this.supplierService.registerSupplier(this.supplier).subscribe(
          res=>{
            this.addSupplier.reset();
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
  respondToComplaints(){

  }

  adminLogout(){
    
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
