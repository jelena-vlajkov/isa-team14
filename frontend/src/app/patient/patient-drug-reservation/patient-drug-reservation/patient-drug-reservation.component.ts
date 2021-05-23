import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import {Medication} from '../../../model/medications/medication'

@Component({
  selector: 'app-patient-drug-reservation',
  templateUrl: './patient-drug-reservation.component.html',
  styleUrls: ['./patient-drug-reservation.component.css']
})
export class PatientDrugReservationComponent implements OnInit {

  isLinear = true;
  public firstFormGroup: FormGroup;
  public secondFormGroup: FormGroup;
  public thirdFormGroup: FormGroup;

  public medications : Medication[] = new Array();
  public searchedMedication : Medication;
  public maxDate : Date;
  public chosenDate : Date;
  public chosenMedication : Medication;


  constructor(private formBuilder: FormBuilder, private authenticationService : AuthenticationService ) { }

  ngOnInit(): void {
    this.maxDate = new Date();

    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
        
    });
    this.thirdFormGroup = this.formBuilder.group({
        thirdCtrl: ['', Validators.required]
    });

  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  searchMedication() {
    this.searchedMedication = this.firstFormGroup.controls.firstCtrl.value;
    console.log(this.searchedMedication);

  }

  onDateChange(chosenDate) {
    this.chosenDate = chosenDate;
    console.log(this.chosenDate);
  }

  onChangeMedication(chosenMed) {
    this.chosenMedication = chosenMed;
  }



}
