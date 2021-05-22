
import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { MatStepper } from '@angular/material/stepper';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

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

  public maxDate: Date;
  
  
  public startTime: String = '00:00';
  public endTime : String = '00:00';
  public minTimeFinishing: String = '00:00';
  public disabledEndTime: Boolean = true;

  public counselingDate : String;

  constructor(private authenticationService : AuthenticationService, private formBuilder: FormBuilder) { }

  @ViewChild('stepper') stepper: MatStepper

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
  }

  ngAfterViewInit() {}

  patientLogOut(){
    this.authenticationService.logout();
  }

  onDateChange(chosenDate) {
    console.log(chosenDate);
    this.counselingDate = chosenDate;
  }

  onStartTimeChange(chosenDate) {
    console.log(chosenDate);
    this.startTime = chosenDate;
    this.endTime = chosenDate;
    this.disabledEndTime = false;
    this.minTimeFinishing = chosenDate;
  }

  onEndTimeChange(chosenDate) {
    console.log(chosenDate);
    this.endTime = chosenDate;
  }

  dateNextButton() {
    if (!this.firstFormGroup.valid) {
     alert('Choose date and time');
    } else {
      
    }
  }



}
