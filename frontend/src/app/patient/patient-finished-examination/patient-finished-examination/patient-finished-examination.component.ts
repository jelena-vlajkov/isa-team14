import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import { Appointment} from '../../../model/appointment/appointment';
import {MatSortModule} from '@angular/material/sort';

@Component({
  selector: 'app-patient-finished-examination',
  templateUrl: './patient-finished-examination.component.html',
  styleUrls: ['./patient-finished-examination.component.css']
})
export class PatientFinishedExaminationComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  ngOnInit(): void {
    this.getFinishedPatientsExamination();
  }

  public examinations : Appointment[] = new Array();
  

  getFinishedPatientsExamination(){
    console.log("ovde")
    this.patientService.getFinishedPatientsExaminations(this.authenticationService.getUserValue().id).subscribe(data =>
      {
        this.examinations = data;
        console.log(this.examinations)
      });
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  sortData(sort: Sort){
    const data = this.examinations.slice();
    if (!sort.active || sort.direction === '') {
      this.examinations = data;
      return;
    }

    this.examinations = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      console.log(this.examinations)
      switch (sort.active) {
        case 'startTime': return compare(a.startTime, b.startTime, isAsc)  
        case 'endTime': return compare(a.endTime, b.endTime, isAsc);    
        case 'cost': return compare(a.appointmentCost, b.appointmentCost, isAsc);   
        case 'pharmName': return compare(a.medicalStaffName, b.medicalStaffName, isAsc);   
        case 'duration': return compare(a.duration, b.duration, isAsc);  
        default: return 0;
      }
     });
  
  
  }

}

function compare(a: Number | String | Date, b: Number | String | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
