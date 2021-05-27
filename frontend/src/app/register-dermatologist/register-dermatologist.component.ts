import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Address } from '@app/model/address/address';
import { Gender } from '@app/model/users/patient/gender';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { Role } from '@app/model/users/role';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Observable} from 'rxjs';
import {filter, map, startWith} from 'rxjs/operators';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { Dermatologist } from '@app/model/users/dermatologist/dermatologist';
import { DermatologistService } from '@app/service/dermatologist/dermatologist.service';
import { AuthenticationService } from '@app/service/user';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';

const allowMultiSelect = true;

@Component({
  selector: 'app-register-dermatologist',
  templateUrl: './register-dermatologist.component.html',
  styleUrls: ['./register-dermatologist.component.css']
})
export class RegisterDermatologistComponent implements OnInit {
  addDermatologist : FormGroup;
  addAdminForm : FormGroup;
  selectedGender;

  displayedColumns: string[] = ['name', 'average_grade', 'address', 'pick'];
  address : Address;
  name : string;
  surname : string;
  phone : string;
  email : string;
  password : string;
  confirmPassword : string;
  gender : Gender;
  dateOfBirth : Date;
  selectedDate;


  minDateOfBirth : Date;
  maxDateOfBirth : Date;

  admin_location : Address;
  admin_location_input: String;
  public pharmacies : Pharmacy[]= new Array();
  @ViewChild(GooglePlacesComponent) googleplaces;
  filteredOptions: Observable<string[]>;
  public dataSource;
  public chosenPharmacies : Pharmacy[] = new Array();
  dataSource2 = new MatTableDataSource<Pharmacy>();
  public dermatologist: Dermatologist;
  public sysAdmin;
  constructor(private systemAdminService : SysadminRegistrationService ,private authenticationService:AuthenticationService, private pharmacyService : PharmacyService, private router:Router, private dermatologistService : DermatologistService) { }

  ngOnInit(): void {
    this.loadSystemAdmin();
    this.addDermatologist = new FormGroup({
      'name' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'mail' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, [Validators.required,Validators.minLength(8)]),
      'confirmpassword' : new FormControl(null, [Validators.required,Validators.minLength(8)])
        });
        this.maxDateOfBirth = new Date();
        this.minDateOfBirth = new Date();
        this.minDateOfBirth.setFullYear(this.minDateOfBirth.getFullYear() - 180);
    this.loadAllPharmacies();
  }

  loadAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
        this.dataSource = new MatTableDataSource(data);
      });
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
  registerDermatologist(){

    this.name = this.addDermatologist.value.name;
    this.surname = this.addDermatologist.value.surname;
    this.phone = this.addDermatologist.value.telephone;
    this.email = this.addDermatologist.value.mail;
    this.password = this.addDermatologist.value.password;
    this.confirmPassword = this.addDermatologist.value.confirmpassword;
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
    }else{
      this.address = this.googleplaces.address;
      this.gender = this.selectedGender;
      this.dateOfBirth = this.selectedDate;
      var role : Role;
      role = Role.Dermatologist;
      var auths : Number[] = new Array();

      if(this.password === this.confirmPassword){
         this.dermatologist = new Dermatologist(null, this.name, this.surname, this.dateOfBirth, this.phone, this.email.toLowerCase(), this.password, this.gender, this.address, role, auths, this.chosenPharmacies);

        this.dermatologistService.addDermatologist(this.dermatologist).subscribe(
          res=>{
            this.addDermatologist.reset();
            this.googleplaces = null;
            alert('Success');
            this.router.navigate(['/admin']);
          },
          error=>{
            alert("Failed - email address already in use! Please enter new one!");
          });
      }else{
        alert('Passwords do not match!');
      }

    }

  }
  pickPharmacy(pharmacy : Pharmacy){
    if(!this.chosenPharmacies.find(x => x === pharmacy)){
      this.chosenPharmacies.push(pharmacy);
      this.dataSource2.data = this.chosenPharmacies;
    }
  }
  removePharmacy(pharmacy: Pharmacy){
    this.chosenPharmacies = this.chosenPharmacies.filter(obj => obj !== pharmacy);
    this.dataSource2.data = this.chosenPharmacies;
  }
  applyFilter(filterValue: string){
      this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  respondToComplaints(){}
  adminLogout(){
    this.authenticationService.logout();
  }}
