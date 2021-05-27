import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import {MatSortModule} from '@angular/material/sort';
import {PatientDrugReservation} from '@app/model/users/patient/patientDrugReservation';



@Component({
  selector: 'app-patient-reserved-drugs',
  templateUrl: './patient-reserved-drugs.component.html',
  styleUrls: ['./patient-reserved-drugs.component.css']
})
export class PatientReservedDrugsComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  public reservations : PatientDrugReservation[] = new Array();
  

  ngOnInit(): void {
    this.patientService.getDrugReservationForPatient(this.authenticationService.currentUserValue.id).subscribe(
      data => {
        this.reservations = data;
      },
      err => {
        alert("There is no medication reservations for this patient");
      }
    );
    
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  cancelReservation(reservation : PatientDrugReservation) {
    this.patientService.cancelDrugReservation(reservation.id).subscribe(
     res => {
      alert('Success');
      location.reload(); 
     }, 
     err => {
      alert("There is less than 24h to cancel this reservation");
     }
    )
  }

}
