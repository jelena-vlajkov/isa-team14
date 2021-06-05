import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { PatientService} from '../../../service/patient/patient.service';
import {Grade} from '@app/model/users/patient/Grade'
import {Pharmacist} from '@app/model/users/pharmacist/pharmacist'
import {Pharmacy} from '@app/model/pharmacy/pharmacy'
import {Medication} from '@app/model/medications/medication'
import {Dermatologist} from '@app/model/users/dermatologist/dermatologist'

@Component({
  selector: 'app-patient-new-grade',
  templateUrl: './patient-new-grade.component.html',
  styleUrls: ['./patient-new-grade.component.css']
})
export class PatientNewGradeComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  gradeTypes: String[] = ['Grade medication', 'Grade pharmacist', 'Grade dermatologist', 'Grade pharmacy'];
  isTypeChosen : boolean = false;
  isMedicationChosen : boolean = false;
  isPharmacistChosen : boolean = false;
  isDermatologistChosen : boolean = false;
  isPharmacyChosen : boolean = false;
  selectedType : String;
  isObjectChosen : boolean = false;
  grades : Number[] = [1, 2, 3, 4, 5];
  isGradeSelected : boolean = false;
  

  public newGrade : Grade;
  public medications : Medication[] = new Array();
  public selectedMedication : Medication;
  public selectedGrade : Number;
  public pharmacies : Pharmacy[] = new Array();
  public selectedPharmacy : Pharmacy;
  public pharmacists : Pharmacist[] = new Array();
  public selectedPharmacist : Pharmacist;
  public selectedDermatologist : Dermatologist;
  public dermatologists : Dermatologist[] = new Array();


  ngOnInit(): void {
  }
 
  

  patientLogOut(){
    this.authenticationService.logout();
  }

  typeChange(gradeType) {
    this.selectedType = gradeType;
    if (this.selectedType === "Grade medication") {
      this.isTypeChosen = true;

      this.patientService.findMedicationsForPatientGrading(this.authenticationService.currentUserValue.id).subscribe(
        data => {
          this.medications = data;
        },
        err => {
          alert("No medications");
        }
      );


    }else if (this.selectedType === "Grade pharmacist") {
      this.isTypeChosen = true;

      this.patientService.findPharmacistsForPatientGrading(this.authenticationService.currentUserValue.id).subscribe(
        data => {
          this.pharmacists = data;
        },
        err => {
          alert("No pharmacists")
        }
      );


    }
    else if (this.selectedType === "Grade dermatologist") {     
      this.isTypeChosen = true;

      this.patientService.findDermatologistsForPatientGrading(this.authenticationService.currentUserValue.id).subscribe(
        data => {
          this.dermatologists = data;
        },
        err => {
          alert("No dermatologists")
        }
      )

    }
    else if (this.selectedType === "Grade pharmacy") {
      this.isTypeChosen = true;

      this.patientService.getPharmaciesForPatientGrading(this.authenticationService.currentUserValue.id).subscribe(
        data => {
          this.pharmacies = data;
        },
        err => {
          alert("No pharmacies")
        }
      );
    }

  }

  medicationChoose(medication) {
    this.selectedMedication = medication;
    this.isMedicationChosen = true;
    this.isObjectChosen = true;
    this.isPharmacistChosen = false;
    this.isDermatologistChosen  = false;
    this.isPharmacyChosen = false;
  }

  pharmacyChoose(pharmacy) {
    this.selectedPharmacy = pharmacy;
    this.isPharmacyChosen = true;
    this.isObjectChosen = true;
    this.isMedicationChosen = false;
    this.isPharmacistChosen = false;
    this.isDermatologistChosen  = false;
  }

  pharmacistChoose(pharmacist) {
    this.selectedPharmacist = pharmacist;
    this.isPharmacistChosen = true;
    this.isObjectChosen = true;
    this.isDermatologistChosen  = false;
    this.isMedicationChosen = false;
    this.isPharmacyChosen = false;

  }

  dermatologistChoose(dermatologist) {
    this.selectedDermatologist = dermatologist;
    this.isDermatologistChosen = true;
    this.isObjectChosen = true;
    this.isPharmacistChosen  = false;
    this.isMedicationChosen = false;
    this.isPharmacyChosen = false;

  }



  gradeChoose(grade) {
    this.selectedGrade = grade;
    this.isGradeSelected = true;

  }

  newGradeClick() {
    if(this.isMedicationChosen && this.isGradeSelected) {
      this.newGrade = new Grade;
      this.newGrade.grade = this.selectedGrade;
      this.newGrade.medicationId = this.selectedMedication.id;
      this.newGrade.gradeType = "MedicationGrade";
      this.newGrade.patientId = this.authenticationService.currentUserValue.id;
      
      this.patientService.newMedicationGrade(this.newGrade).subscribe(
        res => {
          alert("Success");
          location.reload();
        },
        err => {
          alert("Failed to grade. U have already done this")
          location.reload();
        }
      );
    }

    if(this.isPharmacyChosen && this.isGradeSelected) {
      this.newGrade = new Grade;
      this.newGrade.grade = this.selectedGrade;
      this.newGrade.pharmacyId = this.selectedPharmacy.id;
      this.newGrade.gradeType = "PharmacyGrade";
      this.newGrade.patientId = this.authenticationService.currentUserValue.id;
      
      this.patientService.newPharmacyGrade(this.newGrade).subscribe(
        res => {
          alert("Success");
          location.reload();
        },
        err => {
          alert("Failed to grade. U have already done this")
          location.reload();
        }
      );
    }

    if(this.isPharmacistChosen && this.isGradeSelected) {
      this.newGrade = new Grade;
      this.newGrade.grade = this.selectedGrade;
      this.newGrade.pharmacistId = this.selectedPharmacist.id;
      this.newGrade.gradeType = "PharmacistGrade";
      this.newGrade.patientId = this.authenticationService.currentUserValue.id;
      
      this.patientService.newPharmacistGrade(this.newGrade).subscribe(
        res => {
          alert("Success");
          location.reload();
        },
        err => {
          alert("Failed to grade. U have already done this")
          location.reload();
        }
      );
    }

    if(this.isDermatologistChosen && this.isGradeSelected) {
      this.newGrade = new Grade;
      this.newGrade.grade = this.selectedGrade;
      this.newGrade.dermatologistId = this.selectedDermatologist.id;
      this.newGrade.gradeType = "DermatologistGrade";
      this.newGrade.patientId = this.authenticationService.currentUserValue.id;
      
      this.patientService.newDermatologistGrade(this.newGrade).subscribe(
        res => {
          alert("Success");
          location.reload();
        },
        err => {
          alert("Failed to grade. U have already done this")
          location.reload();
        }
      );
    }

  }


}

