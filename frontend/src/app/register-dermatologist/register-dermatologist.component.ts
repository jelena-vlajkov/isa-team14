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

  displayedColumns: string[] = ['id', 'name', 'average_grade', 'address', 'pick'];
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
  public pharmacies : Pharmacy[]= new Array();
  @ViewChild(GooglePlacesComponent) googleplaces;
  filteredOptions: Observable<string[]>;
  public dataSource;
  public chosenPharmacies : Pharmacy[] = new Array();
  dataSource2 = new MatTableDataSource<Pharmacy>();
  public dermatologist: Dermatologist;
  constructor(private authenticationService:AuthenticationService, private pharmacyService : PharmacyService, private router:Router, private dermatologistService : DermatologistService) { }

  ngOnInit(): void {
    this.addDermatologist = new FormGroup({
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

  loadAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
        this.dataSource = new MatTableDataSource(data);
      });
  }

  registerDermatologist(){

    this.name = this.addDermatologist.value.name;
    this.surname = this.addDermatologist.value.surname;
    this.phone = this.addDermatologist.value.telephone;
    this.email = this.addDermatologist.value.mail;
    this.password = this.addDermatologist.value.password;
    this.confirmPassword = this.addDermatologist.value.confirmpassword;
    if(this.googleplaces===undefined){
      alert("Please fill the address!");
    }else{
      this.address = this.googleplaces.address;
      this.gender = this.selectedGender;
      this.dateOfBirth = this.selectedDate;
      var role : Role;
      role = Role.Dermatologist;
      var auths : Number[] = new Array();

      if(this.password === this.confirmPassword){
         this.dermatologist = new Dermatologist(this.name, this.surname, this.dateOfBirth, this.phone, this.email, this.password, this.gender, this.address, role, auths, this.chosenPharmacies);

        this.dermatologistService.addDermatologist(this.dermatologist).subscribe(
          res=>{
            this.addDermatologist.reset();
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
