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
import {WorkDay} from "@app/model/schedule/workDay";
import { AverageGrade } from '@app/model/users/averageGrade';
import {WorkdayService} from "@app/service/workday/workday.service";
import {moment} from "ngx-bootstrap/chronos/test/chain";
import {AppointmentService} from "@app/service/appointment/appointment.service";

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
  isPharmacistsEmpty:boolean = false;
  setWorkTime : boolean = false;
  pharmacists : Pharmacist[]= new Array();
  workdays:WorkDay[] = new Array();
  public Address : Address;

  private StringIsNumber = value => isNaN(Number(value)) === false;
  workTime : FormGroup;
  currentDate: Date;
  pharamcistAddress:Address;
  constructor(private authenticationService:AuthenticationService
              ,private router:Router
              ,private pharmacistService:PharmacistService
              ,private pharmacyAdminService:PharmacyAdminService
              ,private workdayService:WorkdayService
              ,private appointmentService:AppointmentService
  ) { }

  ngOnInit(): void {
    this.addPharmacist = new FormGroup({
      'name' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null,  [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'gender': new FormControl(null, Validators.required),
      'dob' : new FormControl(null, Validators.required),
      'telephone' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'mail' : new FormControl(null, [Validators.required, Validators.email]),
      'password' : new FormControl(null, [Validators.required,Validators.minLength(8)]),
      'confirmpassword' : new FormControl(null, [Validators.required,Validators.minLength(8)]),

    });
    this.workTime = new FormGroup({
      'startTime' : new FormControl(null,  [Validators.required]),
      'endTime' : new FormControl(null,  [Validators.required]),
      'date': new FormControl(null,  [Validators.required])
      });
    this.maxDateOfBirth = new Date();
    this.minDateOfBirth = new Date();

    this.currentDate = new Date();
    this.maxDateOfBirth.setFullYear(this.maxDateOfBirth.getFullYear() - 18);
    console.log(this.maxDateOfBirth);
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


  showRegisterPharmacistDialog() {
    this.pharmacistsInPharmacy = false;
    this.registerPharmacistDialog = true;
    this.setWorkTime = false;
  }

  cancelRegisterPharmacistDialog() {
    this.pharmacistsInPharmacy = true;
    this.registerPharmacistDialog = false;
    this.setWorkTime = false;
  }

  deletePharmacist(id: Number) {
    this.appointmentService.occupiedCounselingsExists(id).subscribe(result=>{
      if(!result){
        this.pharmacistService.deletePharmacist(id,this.pharmacy.id).subscribe(result => {
          this.getPharmacistByPharmacy();
        });
      }
      else{
        alert("Can't delete this pharmacist at the moment.There are already scheduled future counselings");
      }
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

  registrationNextPage() {
    this.pharmacistsInPharmacy = false;
    this.registerPharmacistDialog = false;
    this.setWorkTime = true;
    this.pharamcistAddress = this.googleplaces.address;
  }

  addWorkDay() {
    var inputDate=new Date(this.workTime.value.date);
    var startTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + this.workTime.value.startTime);
    var endTime=new Date(inputDate.toString().split(":")[0].slice(0,-2) + this.workTime.value.endTime);

    let workday = this.workdays.filter(workday => workday.date == this.workTime.value.date
      && (workday.startTime.getTime() <= endTime.getTime()) || (startTime.getTime() <= workday.endTime.getTime()));
    if(this.workTime.value.startTime > this.workTime.value.endTime  || (inputDate.getTime() < this.currentDate.getTime())){
      alert("Invalid work time input.Check if start time is after end time or selected date is before current date.Work time must be at least one hour.");
    }
    else if(workday.length!=0){
      alert("Invalid work time.Check if your inputs are overlapping with some of already existing workdays.");
    }
    else{
      let workDay=new WorkDay(null,new Date(this.workTime.value.date),startTime
        ,endTime,null,null);
      console.log(workDay);
      this.workdays.push(workDay);
    }
  }

  isWorkDaysEmpty() {
    return this.workdays.length==0;
  }

  deleteWorkday(workday: WorkDay) {
    this.workdays=this.workdays.filter( wd => wd.endTime!=workday.endTime
      || wd.startTime!=workday.startTime || wd.date!=workday.date);
  }

  registerPharmacistSubmitted() {
  if (this.pharamcistAddress === undefined) {
      alert('Wrong address input. Please add an address.');
   } else {
    // console.log(this.googleplaces.address)
      let pharmacist = new Pharmacist(null, this.addPharmacist.value.name, this.addPharmacist.value.surname
        , this.addPharmacist.value.dob, this.addPharmacist.value.telephone, this.addPharmacist.value.mail
        , this.addPharmacist.value.password, this.addPharmacist.value.gender, null, null
        , null, this.pharmacy, null, false);
        pharmacist.address = this.pharamcistAddress;
        pharmacist.averageGrade = 0.0;
      this.pharmacistService.registerPharmacist(pharmacist).subscribe(result => {
        for (let i = 0; i < this.workdays.length; i++) {
          this.workdays[i].pharmacy = this.pharmacy;
          this.workdays[i].medicalStaff = result;
          console.log(this.workdays[i]);
          this.workdayService.addWorkday(this.workdays[i]).subscribe(result => {
          });
        }
        this.getPharmacistByPharmacy();
        this.pharmacistsInPharmacy = true;
        this.setWorkTime = false;
        this.registerPharmacistDialog = false;

      });
    }
  }

  saveAddress() {
    console.log(this.googleplaces.address);
    this.pharamcistAddress = this.googleplaces.address;
  }
}
