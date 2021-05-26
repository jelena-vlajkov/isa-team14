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
import { MedicationsToRecommend } from '@app/model/pharmderm/medicationstorecommend';
import { CreaeteReservation } from '@app/model/pharmderm/createreservation';
import { CreatePenalty } from '@app/model/pharmderm/createpenalty';
import { SaveReport } from '@app/model/pharmderm/createreport';
import {PatientAppointmentDTO} from '@app/model/pharmderm/patientappointmentdto'

@Component({
  selector: 'patient-examination',
  templateUrl: './patient-examination.component.html',
  styleUrls: ['./patient-examination.component.css']
})
export class PatientExaminationComoponent {
  
    displayedColumns: string[] = ['position', 'name', 'dosage', "available", '#'];
    displayedColumns2: string[] = ['position', 'startTime', 'endTime', '#'];
    constructor(private authService: AuthenticationService, private router : Router, private datePipe: DatePipe, private employeeService : EmployeeService) { }
    public todaysDate : string;
    public todaysDateDate : Date;
    public appointments : Appointment[];
    public availableAppointments : Appointment[];
    public showSearchResults : boolean;
    public searchAppointmentForm : FormGroup;
    public searchMedicationsForm : FormGroup;
    public showSearchResultsForMedications : boolean;
    public addReportForm : FormGroup;
    public a : Appointment;
    public patientId : Number;
    

    @ViewChild(MatPaginator) paginator: MatPaginator;


    ngOnInit() {
      if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
        this.router.navigate(["/employee-welcome"]);
      }
      this.todaysDateDate = new Date();
      this.todaysDateDate.setDate(this.todaysDateDate.getDate() + 1);
      this.showSearchResultsForMedications = false;
      this.todaysDate = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
      this.patientId = history.state.data;

      if (this.patientId === undefined) {
          this.router.navigate(["/dashboard"])
      }
      this.employeeService.getAppointmentForPatient(Number(localStorage.getItem("userId")), this.todaysDate, this.patientId).subscribe(
        data => {
          this.a = data;
            this.a.startDateString = this.datePipe.transform(this.a.startTime, 'hh:mm');
            this.a.prescribedMedications = [];
            this.a.canAddPenalty = true;
            console.log(this.a.finished)
          
          this.showSearchResults = false;
          this.searchAppointmentForm = new FormGroup({
            'date' : new FormControl(null, []),
          });
          this.searchMedicationsForm = new FormGroup({
            'name' : new FormControl("", [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        
          })
          this.addReportForm = new FormGroup({
            'details' : new FormControl("", [Validators.required]),
        
          })

        }, 
        error => {
          alert(error);
        })
    }
    

    isPharmacist() {
      let user = this.authService.currentUserValue;
      return user.role === 'Pharmacist'; 
    }
  
    isDermatologist() {
      let user = this.authService.currentUserValue;
      return user.role === 'Dermatologist'; 
    }


    addPenalty(a : Appointment) {
      let penalty = new CreatePenalty();
      penalty.appointmentId = a.id;
      penalty.patientId = a.patientId;

      this.employeeService.addPenalty(penalty).subscribe(
        data => {
          a.finished = true;
          alert("Successfully reported patient!");
        }, error => {
          alert(error)
        }
      )
    }

    addReport(a : Appointment) {
      let report = new SaveReport();
      report.medicalStaffId = Number(localStorage.getItem("userId"));
      report.medications = a.prescribedMedications;
      report.patientId = a.patientId;
      report.pharmacyId = a.pharmacyId;
      let details = this.addReportForm.controls.details.value;
      console.log(details)
      report.reportNotes = details;
      this.employeeService.addReport(report).subscribe(
        data => {
          this.employeeService.finishAppointment(a.id).subscribe(
            data => {
              alert("Successfully added report!");
              a.finished = true;

            }, error => {
              alert(error)
            }
          )
        }, error => {
          alert(error);
        }
      )
    }

    recommendSimilar(medication : MedicationsToRecommend, a : Appointment) {
      console.log(medication)
      this.employeeService.recommendSimilarMedications(medication.id, a.pharmacyId).subscribe(
        data => {
          a.medicationsForPatients = data;
          this.showSearchResultsForMedications = true;
        },
        error => {
          alert(error);
        }
      )
    }

    searchMedications(a : Appointment) {
      console.log(a)
      a.canAddPenalty = false;
      this.employeeService.recommendAvailableMedications(a.patientId, a.pharmacyId).subscribe(
        data => {
          a.medicationsForPatients = [];
          for (let medication of data) {
            medication.prescribed = false;
            a.medicationsForPatients.push(medication);
          }
          this.showSearchResultsForMedications = true;
        },
        error => {
          alert(error);
        }
      )
    }

    scheduleAppointment(a : Appointment, app : Appointment) {
      console.log(a)
      let schedule = new Appointment();
      schedule.startTime = a.startTime;
      schedule.endTime = a.endTime;
      schedule.medicalStaffId = a.medicalStaffId;
      schedule.patientId = a.patientId;
      schedule.pharmacyId = app.pharmacyId;
      console.log(schedule)
      schedule.medicalStaffId = Number(localStorage.getItem("userId"));
      schedule.patientId = app.patientId;
      
      this.employeeService.scheduleAppointment(schedule).subscribe(
        response => {
          alert("Successfully scheduled appoinmtnet");

        }, error => {
          alert(error);
        }
      )
    }
    prescribeMedication(medication : MedicationsToRecommend, a : Appointment) {
      a.prescribedMedications.push(medication.name);
      let reservation = new CreaeteReservation();
      reservation.medicationId = medication.id;
      reservation.patientId = a.patientId;
      reservation.pharmacyId = a.pharmacyId;
      console.log(medication)
      reservation.therapyDays = medication.days;

      this.employeeService.addDrugReservation(reservation).subscribe(
        response => {
          a.prescribedMedications.push(medication.name);
          medication.prescribed = true;
          alert("Successfully prescribed medication for patient!");
        }, error => {
          alert(error);
        }
      )
    }
    searchAppointments(a : Appointment) {
      let date = this.searchAppointmentForm.controls.date.value;
      if (date === null) {
        alert("Please select a date!");
      } else {
        let stringDate = this.datePipe.transform(date, 'dd.MM.yyyy.'); 
        let appointmentPatientDTO = new PatientAppointmentDTO();
        appointmentPatientDTO.date = stringDate;
        appointmentPatientDTO.medicalStaffId = Number(localStorage.getItem("userId"));
        appointmentPatientDTO.patientId = a.patientId;
        appointmentPatientDTO.pharmacyId = a.pharmacyId;

        this.employeeService.getAvailableAppointmentsForPatient(appointmentPatientDTO).subscribe(
          data => {
            let availableapps = data;
            this.showSearchResults = true;

            for (let a of availableapps) {
              a.startDateString = this.datePipe.transform(a.startTime, 'hh:mm');
              a.endDateString = this.datePipe.transform(a.endTime, 'hh:mm');
            }

            a.availableAppointment = availableapps;
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
