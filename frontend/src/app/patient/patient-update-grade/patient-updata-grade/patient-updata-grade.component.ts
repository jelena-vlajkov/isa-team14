import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { PatientService} from '../../../service/patient/patient.service';
import {UpdateGrade} from '@app/model/users/patient/updateGrade';
import { Grade } from '@app/model/users/patient/Grade';


@Component({
  selector: 'app-patient-updata-grade',
  templateUrl: './patient-updata-grade.component.html',
  styleUrls: ['./patient-updata-grade.component.css']
})
export class PatientUpdataGradeComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService ) { }

  isObjectChosen : boolean = false;
  gradesTypes : Number[] = [1, 2, 3, 4, 5];
  isGradeSelected : boolean = false;
  public selectedNewGrade : Number;

  public grades : UpdateGrade[] = new Array();
  public newGrade : Grade;
  public selectedGrade : UpdateGrade;
  public selectedGradeId : Number;

  ngOnInit(): void {
    this.patientService.getAllGrades(this.authenticationService.currentUserValue.id).subscribe(
      data => {
        this.grades = data;
        console.log(this.grades)
      }
    );
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  chooseObject(oldGrade, oldGradeId) {
    this.selectedGrade = oldGrade;
    this.selectedGradeId = oldGradeId;
    this.isObjectChosen = true;
    console.log(this.selectedGrade)

  }

  gradeChoose(grade) {
    this.selectedNewGrade = grade;
    this.isGradeSelected = true;
  }

  updateGradeClick() {
    this.newGrade = new Grade;
    this.newGrade.id = this.selectedGradeId;
    this.newGrade.dermatologistId = this.selectedGrade.dermatologistId;
    this.newGrade.grade = this.selectedNewGrade;
    this.newGrade.gradeType = this.selectedGrade.gradeType;
    this.newGrade.medicationId = this.selectedGrade.medicationId;
    this.newGrade.pharmacistId = this.selectedGrade.pharmacistId;
    this.newGrade.pharmacyId = this.selectedGrade.pharmacyId;
    this.newGrade.patientId = this.authenticationService.currentUserValue.id;

    
    console.log(this.newGrade);
    
    
    this.patientService.updateGivenGrade(this.newGrade).subscribe(
      res => {
        alert("Success")
        location.reload();
      },
      err => {
        alert("Fail")
        location.reload();
      }
    );

  }

}
