import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Medication} from '../../../model/medications/medication';
import {MedicationService} from '../../../service/medication/medication.service';
import {PatientService} from '../../../service/patient/patient.service';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import {CreaeteReservation} from '@app/model/pharmderm/createreservation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-drug-reservation',
  templateUrl: './patient-drug-reservation.component.html',
  styleUrls: ['./patient-drug-reservation.component.css']
})
export class PatientDrugReservationComponent implements OnInit, AfterViewInit {

  isLinear = true;
  public firstFormGroup: FormGroup;
  public secondFormGroup: FormGroup;
  public thirdFormGroup: FormGroup;
  public fourthFormGroup : FormGroup;

  public medications : Medication[] = new Array();
  public searchedMedication : String;
  public maxDate : Date;
  public chosenDate : Date;
  public chosenMedicationId : Number;
  public pharmacies : Pharmacy[] = new Array();
  public chosenPharmacy : Pharmacy;
  public isPharmChosen : Boolean;
  public createReservation : CreaeteReservation;


  constructor(private formBuilder: FormBuilder, private authenticationService : AuthenticationService, private medicationService : MedicationService,
    private patientService : PatientService, private router : Router ) { }

  ngOnInit(): void {
    this.maxDate = new Date();
    this.isPharmChosen = false;

    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
        
    });
    this.thirdFormGroup = this.formBuilder.group({
        thirdCtrl: ['', Validators.required]
    }); 
    this.fourthFormGroup = this.formBuilder.group({
      
  });

  }

  ngAfterViewInit() {

  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  searchMedication() {
    this.searchedMedication = this.firstFormGroup.controls.firstCtrl.value;

    if(this.searchedMedication === ''){
      alert("Insert medication name")
    }else{

      this.medicationService.findByName(this.searchedMedication).subscribe(
        data => {
          this.medications = data;
      },
      err => {
        alert('This medication does not exists')
      }
      );

    }  

  }

  onDateChange(chosenDate) {
    this.chosenDate = chosenDate;
    console.log(this.chosenDate);
  }

  onChangeMedication(chosenMed) {
    this.chosenMedicationId = chosenMed[0];
  }

  medicationButtonNext() {
    console.log(this.chosenMedicationId)
    if(!this.firstFormGroup.valid) {
      alert("Search and choose one medicament")
    }else{
      this.getPharmaciesByMedicationId(this.chosenMedicationId);

    }
  }

  getPharmaciesByMedicationId(id : Number) {
    this.patientService.getPharmaciesByMedicationId(id).subscribe( 
      res => {
        this.pharmacies = res;
      },
      err => {
        alert("There is no pharmay with this medication");
      }
    );
  }

  choosePharmacy(pharmacy : Pharmacy) {
    this.chosenPharmacy = pharmacy;
    console.log(pharmacy);
    this.isPharmChosen = true;
  }

  reserveMedication() {
    this.createReservation = new CreaeteReservation();
    this.createReservation.medicationId = this.chosenMedicationId;
    this.createReservation.pharmacyId = this.chosenPharmacy.id;
    this.createReservation.therapyDays = 10;
    this.createReservation.patientId = this.authenticationService.currentUserValue.id;
    this.createReservation.expirationDate = this.chosenDate;
    console.log(this.createReservation);
    this.patientService.createDrugReservation(this.createReservation).subscribe(
      res => {
        alert('Success');
        location.reload();
      },
      err => {
        alert('Failed to reserve medication')
      }
    );
  }



}
