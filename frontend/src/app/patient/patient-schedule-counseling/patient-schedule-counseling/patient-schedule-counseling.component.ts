
import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { MatStepper } from '@angular/material/stepper';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { PatientService } from '@app/service/patient/patient.service';
import * as moment from 'moment';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import {Sort} from '@angular/material/sort';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import {PatientScheduleCounseling} from '@app/model/users/patient/PatientScheduleCounseling'
import { Router } from '@angular/router';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-patient-schedule-counseling',
  templateUrl: './patient-schedule-counseling.component.html',
  styleUrls: ['./patient-schedule-counseling.component.css']
})
export class PatientScheduleCounselingComponent implements OnInit, AfterViewInit  {

  public isLinear = true;
  public firstFormGroup: FormGroup;
  public secondFormGroup: FormGroup;
  public thirdFormGroup: FormGroup;
  public fourthFormGroup : FormGroup;

  public maxDate: Date;
  
  
  public startTime: String = '00:00';
  public endTime : String = '00:00';
  public minTimeFinishing: String = '00:00';
  public disabledEndTime: Boolean = true;

  public counselingDate : Date;
  public counselingDateStart : String;
  public counselingDateEnd : String;

  public pharmacies : Pharmacy[] = new Array();
  public pharmacists : Pharmacist[] = new Array();
  public chosenPharmacy : Pharmacy;
  public isPharmChosen : Boolean = false;
  public isOnPharmacistChoosing : Boolean = false;

  public choosenPharmacist : Pharmacist;
  public isPharmacistChosen : Boolean = false;


  constructor(private authenticationService : AuthenticationService, private formBuilder: FormBuilder, private patientService : PatientService, private router: Router) { }

  @ViewChild('stepper') stepper: MatStepper
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }
  ngOnInit(): void {

    this.maxDate = new Date();

    this.firstFormGroup = this.formBuilder.group({
      datePicker: ['', Validators.required],
      timePickerStart: ['', Validators.required],
      timePickerEnd: ['', Validators.required]
     });

    this.secondFormGroup = this.formBuilder.group({
    });

    this.thirdFormGroup = this.formBuilder.group({
    });

    this.fourthFormGroup = this.formBuilder.group({
    });
  }

  

  patientLogOut(){
    this.authenticationService.logout();
  }

  onDateChange(chosenDate) {
    console.log(chosenDate);
    this.counselingDate = chosenDate;
  }

  onStartTimeChange(chosenDate) {
    this.startTime = chosenDate;
    this.endTime = chosenDate;
    this.disabledEndTime = false;
    this.minTimeFinishing = chosenDate;

    this.counselingDateStart = moment(this.counselingDate).format('DD.MM.YYYY.')
    this.counselingDateStart = this.counselingDateStart + " " + this.startTime;
    
       
    this.counselingDateEnd = this.counselingDateStart;
    console.log(this.counselingDateStart);
    
  }

  onEndTimeChange(chosenDate) {  
    this.endTime = chosenDate;

    this.counselingDateEnd = moment(this.counselingDate).format('DD.MM.YYYY.') + " " + this.endTime;
    console.log(this.counselingDateEnd);
  
  }

  dateNextButton() {
    if (!this.firstFormGroup.valid) {
     alert('Choose date and time');
    } else {
      this.patientService.findAvailablePharmacyByCounselingRange(this.counselingDateStart, this.counselingDateEnd).subscribe(
        data => {
          this.pharmacies = data;
        }, 
        err => {
          alert("No available pharmacies with that date range")
        }
      );
      
    }

    this.isOnPharmacistChoosing = false;
   
  }

  choosePharmacy(pharmacy : Pharmacy) {
    this.chosenPharmacy = pharmacy;
    console.log(pharmacy);
    this.isPharmChosen = true;
   
  }

  pharmacistChoose(pharmacist : Pharmacist) {
    this.choosenPharmacist = pharmacist;
    console.log(pharmacist);
    this.isPharmacistChosen = true;
  }

  pharmacistNextButton() {
    if (this.isPharmacistChosen === false) {
      alert("Choose pharmacist") 
    }

  }

  pharmacyNextButton() {
    if (this.isPharmChosen == false) {
      alert("Choose pharmacy");
    }else {
      this.patientService.findPharmacistsByRangeAndPharmacy(this.chosenPharmacy.id, this.counselingDateStart, this.counselingDateEnd).subscribe(
        data => {
          this.pharmacists = data;
        },
        err => {
          alert("There is no available pharmacists")
        }
      );
    }

    this.isOnPharmacistChoosing = true;
  }

  scheduleCounseling() {
    var schedule = new PatientScheduleCounseling;
    schedule.type = 'Counseling';
    schedule.startTimeRange = this.counselingDateStart;
    schedule.endTimeRange = this.counselingDateEnd;
    schedule.pharmacyId = this.chosenPharmacy.id;
    schedule.pharmacistId = this.choosenPharmacist.id;
    schedule.patientId = this.authenticationService.currentUserValue.id;

    this.patientService.findAndSchedulePatientCounseling(schedule).subscribe(
      res => {
        alert("Success");
        this.router.navigate(['patient/scheduledAppointments']);
      },
      err => {
        alert('Failed to schedule counseling')
      }
    );



  }


  sortData(sort: Sort){
    
    if (this.stepper.selectedIndex == 1) {
      this.isOnPharmacistChoosing = false;
      const data = this.pharmacies.slice();
      if (!sort.active || sort.direction === '') {
        this.pharmacies = data;
        return;
      }
  
      this.pharmacies = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'grade': return compare(a.averageGradeCount, b.averageGradeCount, isAsc);   
          case 'price': return compare(a.counselingCost, b.counselingCost, isAsc);
          default: return 0;
        }
       });
    }

    if(this.stepper.selectedIndex == 2) {
      const data = this.pharmacists.slice();
      if (!sort.active || sort.direction === '') {
        this.pharmacists = data;
        return;
      }

      if (this.stepper._getFocusIndex() === 1) {
        this.isOnPharmacistChoosing = false;
      }
  
      this.pharmacists = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'grade': return compare(a.averageGrade, b.averageGrade, isAsc); 
          default: return 0;
        }
       });
    }

   
  }
}

function compare(a: Number | String, b: Number | String, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

