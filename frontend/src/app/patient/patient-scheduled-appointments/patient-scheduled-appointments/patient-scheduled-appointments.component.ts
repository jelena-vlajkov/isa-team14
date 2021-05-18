import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import { Appointment} from '../../../model/appointment/appointment';
import {MatSortModule} from '@angular/material/sort';

@Component({
  selector: 'app-patient-scheduled-appointments',
  templateUrl: './patient-scheduled-appointments.component.html',
  styleUrls: ['./patient-scheduled-appointments.component.css']
})
export class PatientScheduledAppointmentsComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }
  
  public appointments : Appointment[] = new Array();

  ngOnInit(): void {
    this.patientService.getNotFinishedAppointmentsForPatient(this.authenticationService.getUserValue().id).subscribe(
      data =>
      {
        this.appointments = data;
        console.log(this.appointments)
      }, 
      err => {
        alert('There is no scheduled appointments for you')
      }
      );
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  cancelAppointment(appointment : Appointment) {
    this.patientService.cancelAppointment(appointment.id).subscribe(
     res => {
      alert('Success');
      location.reload(); 
     }, 
     err => {
      alert("There is less than 24h to cancel this appointment");
     }
    )
  }

  sortData(sort: Sort){
    const data = this.appointments.slice();
    if (!sort.active || sort.direction === '') {
      this.appointments = data;
      return;
    }

    this.appointments = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      console.log(this.appointments)
      switch (sort.active) {
        case 'startTime': return compare(a.startTime, b.startTime, isAsc);   
        case 'cost': return compare(a.appointmentCost, b.appointmentCost, isAsc); 
        default: return 0;
      }
     });
    
  
  }

}


function compare(a: Number | String | Date, b: Number | String | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

