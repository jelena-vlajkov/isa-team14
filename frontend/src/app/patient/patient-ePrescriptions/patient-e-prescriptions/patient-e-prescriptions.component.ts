import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import {MatSortModule} from '@angular/material/sort';
import { EPrescription } from '@app/model/medications/ePrescription';

@Component({
  selector: 'app-patient-e-prescriptions',
  templateUrl: './patient-e-prescriptions.component.html',
  styleUrls: ['./patient-e-prescriptions.component.css']
})
export class PatientEPrescriptionsComponent implements OnInit, AfterViewInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  public prescriptions : EPrescription[] = new Array();
  public numOfPenalty : Number;
  public prescriptionsCopy : EPrescription[] = new Array();
  
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  ngOnInit(): void {
    this.patientService.getPatientEPrescriptions(this.authenticationService.getUserValue().id).subscribe(
      data =>
      {
        this.prescriptions = data;
        this.prescriptionsCopy = data;
      }, 
      err => {
        alert('There is no prescriptions for you')
      }
      );


      this.numOfPenalty = Number(localStorage.getItem("patientPenalty"));
      console.log(this.numOfPenalty)
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  typeChange(event){
    if (event.value == 1){
      this.prescriptions = this.prescriptionsCopy;
      this.prescriptions = this.prescriptions.filter(p => p.type === 'New');
    }else if(event.value == 2) {
      this.prescriptions = this.prescriptionsCopy;
      this.prescriptions = this.prescriptions.filter(p => p.type === 'Processed');
    }else if(event.value == 3) {
      this.prescriptions = this.prescriptionsCopy;
      this.prescriptions = this.prescriptions.filter(p => p.type === 'Rejected');
    }else{
      this.prescriptions = this.prescriptionsCopy;
    }
   
 }

  sortData(sort: Sort){
    const data = this.prescriptions.slice();
    if (!sort.active || sort.direction === '') {
      this.prescriptions = data;
      return;
    }

    this.prescriptions = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      console.log(this.prescriptions)
      switch (sort.active) {
        case 'date': return compare(a.date, b.date, isAsc);
        default: return 0;
      }
     });
    
  
  }


}


function compare(a: Number | String | Date, b: Number | String | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
