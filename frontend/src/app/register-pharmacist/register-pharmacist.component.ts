import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from "@app/service/user";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Role} from "@app/model/users";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {Address} from "@app/model/address/address";
import {Gender} from "@app/model/users/patient/gender";
import {PharmacistService} from "@app/service/pharmacist/pharmacist.service";
import {GooglePlacesComponent} from "@app/google-places/google-places.component";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacist} from "@app/model/users/pharmacist/pharmacist";

@Component({
  selector: 'app-register-pharmacist',
  templateUrl: './register-pharmacist.component.html',
  styleUrls: ['./register-pharmacist.component.css']
})
export class RegisterPharmacistComponent implements OnInit {
  addPharmacist:FormGroup;
  dateOfBirth : Date;
  minDateOfBirth : Date;
  maxDateOfBirth : Date;
  selectedGender;
  selectedDate;

  address : Address;
  name : string;
  surname : string;
  phone : string;
  email : string;
  password : string;
  confirmPassword : string;
  gender : Gender;
  @ViewChild(GooglePlacesComponent) googleplaces;
  pharmacy : Pharmacy;
  pharmacistsInPharmacy:boolean = true;
  registerPharmacistDialog:boolean = false;
  pharmacists : Pharmacist[]= new Array();
  isPharmacistsEmpty:boolean = false;
  private StringIsNumber = value => isNaN(Number(value)) === false;
  selectedTimeRange;
  workTime : FormGroup;

  constructor(private authenticationService:AuthenticationService
              ,private router:Router
              ,private pharmacistService:PharmacistService
              ,private pharmacyAdminService:PharmacyAdminService) { }

  ngOnInit(): void {
    this.addPharmacist = new FormGroup({
      'name' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'mail' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, [Validators.required,Validators.minLength(8)]),
      'confirmpassword' : new FormControl(null, [Validators.required,Validators.minLength(8)])
    });
    this.workTime = new FormGroup({
      'mondayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'mondayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'tuesdayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'tuesdayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'wednesdayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'wednesdayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'thursdayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'thursdayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'fridayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'fridayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'saturdayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'saturdayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'sundayStartTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'sundayEndTime' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
    });
    this.maxDateOfBirth = new Date();
    this.minDateOfBirth = new Date();
    this.minDateOfBirth.setFullYear(this.minDateOfBirth.getFullYear() - 180);
    this.pharmacyAdminService.getPharmacyByAdmin(Number(localStorage.getItem('userId'))).subscribe(
      result => {
        this.pharmacy = result;
        this.getPharmacistByPharmacy();
      });

  }

  toArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }

  checkLoggedInUser(){
    return this.authenticationService.getUserValue();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }


  registerPharmacist(){
    this.name = this.addPharmacist.value.name;
    this.surname = this.addPharmacist.value.surname;
    this.phone = this.addPharmacist.value.telephone;
    this.email = this.addPharmacist.value.mail;
    this.password = this.addPharmacist.value.password;
    this.confirmPassword = this.addPharmacist.value.confirmpassword;
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
    }else{
      this.address = this.googleplaces.address;
      this.gender = this.selectedGender;
      this.dateOfBirth = this.selectedDate;
      var role : Role;
      role = Role.Pharmacist;
      var auths : Number[] = new Array();

      if(this.password === this.confirmPassword){
        var pharmacist = new Pharmacist(null, this.name, this.surname, this.dateOfBirth, this.phone
                                        ,this.email.toLowerCase(),this.password, this.gender
                                        , this.address, role, auths,this.pharmacy,null,null,null);

        this.pharmacistService.registerPharmacist(pharmacist).subscribe(
          res=>{
            this.googleplaces = null;
            alert('Success');
            this.router.navigate(['/pharmacy-profile']);
          },
          error=>{
            alert("Failed - email address already in use! Please enter new one!");
          });
      }else{
        alert('Passwords do not match!');
      }

    }

  }


  showRegisterPharmacistDialog() {
    this.pharmacistsInPharmacy = false;
    this.registerPharmacistDialog = true;
  }

  cancelRegisterPharmacistDialog() {
    this.pharmacistsInPharmacy = true;
    this.registerPharmacistDialog = false;
  }

  deletePharmacist(id: Number) {
    this.pharmacistService.deletePharmacist(id).subscribe(result => {
        this.getPharmacistByPharmacy();
    });
  }

  private getPharmacistByPharmacy() {
    this.pharmacistService.getPharmacistsByPharmacy(this.pharmacy.id).subscribe(
      result => {
        this.pharmacists = this.toArray(result);
        if(result.length==0){
          this.isPharmacistsEmpty=true;
        }
      });
  }

  setMondayEndTime() {
    console.log(this.workTime.value.mondayStartTime);
    this.workTime.value.mondayEndTime=this.workTime.value.mondayStartTime+1800;
  }
}
