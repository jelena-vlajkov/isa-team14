import {AfterViewInit, Component, ViewChild, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';
import {AuthenticationService} from '../service/user/authentication.service'
import { DatePipe } from '@angular/common';
import { Appointment } from '@app/model/appointment/appointment';
import { EmployeeService } from '@app/service/employee/employee.service';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Medication } from '@app/model/medications/medication';

@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharmacist.add-report.component.html',
  styleUrls: ['./pharmacist.add-report.component.css']
})
export class PharmacistAddReportComponent {
  
    displayedColumns: string[] = ['position', 'name', 'dosage', "available", '#'];
    displayedColumns2: string[] = ['position', 'startTime', 'endTime', '#'];
    constructor(private authService: AuthenticationService, private router : Router, private datePipe: DatePipe, private employeeService : EmployeeService) { }
    public todaysDate : string;
    public appointments : Appointment[];
    public availableAppointments : Appointment[];
    public showSearchResults : boolean;
    public searchAppointmentForm : FormGroup;
    public searchMedicationsForm : FormGroup;
    public showSearchResultsForMedications : boolean;
    

    @ViewChild(MatPaginator) paginator: MatPaginator;


    ngOnInit() {
      if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
        this.router.navigate(["/employee-welcome"]);
      }
      this.showSearchResultsForMedications = false;
      this.todaysDate = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
      this.employeeService.getScheduledAppointmentsForDate(Number(localStorage.getItem("userId")), this.todaysDate).subscribe(
        data => {
          this.appointments = data;
          for(let appointment of this.appointments) {
            appointment.startDateString = this.datePipe.transform(appointment.startTime, 'hh:mm');
          }
          this.showSearchResults = false;
          this.searchAppointmentForm = new FormGroup({
            'date' : new FormControl(null, []),
          });
          this.searchMedicationsForm = new FormGroup({
            'name' : new FormControl("", [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        
          })

        }, 
        error => {
          alert(error);
        }
      )

      


    }

    searchMedications(a : Appointment) {
      console.log(a)
      this.employeeService.recommendMedications(300).subscribe(
        data => {
          a.medicationsForPatients = data;
          this.showSearchResultsForMedications = true;
        },
        error => {
          alert(error);
        }
      )
    }

    scheduleAppointment() {}
    prescribeMedication(medication : Medication) {}
    searchAppointments() {
      let date = this.searchAppointmentForm.controls.date.value;
      if (date === null) {
        alert("Please select a date!");
      } else {
        let stringDate = this.datePipe.transform(date, 'dd.MM.yyyy.');
        this.employeeService.getAvailable(Number(localStorage.getItem("userId")), stringDate, 100).subscribe(
          data => {
            let availableapps = data;
            // this.availableAppointments = data;
            this.showSearchResults = true;

            for (let a of availableapps) {
              a.startDateString = this.datePipe.transform(a.startTime, 'hh:mm');
              a.endDateString = this.datePipe.transform(a.endTime, 'hh:mm');
            }

            this.availableAppointments = availableapps;
          }, 
          error => {
            alert(error)
            
            this.showSearchResults = false;
          }
        )
      }
    }
    logout() {
      this.authService.logout();
    }
  }
  
  export interface PeriodicElement {
    name: string;
    position: number;
  }
  
  const ELEMENT_DATA: PeriodicElement[] = [
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'}
  ];