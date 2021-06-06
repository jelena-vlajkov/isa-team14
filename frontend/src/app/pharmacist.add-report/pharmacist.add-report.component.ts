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
import { PatientAppointmentDTO } from '@app/model/pharmderm/patientappointmentdto';
import { WorkDay } from '@app/model/schedule/workDay';
import { PrescribeMedication } from '@app/model/pharmderm/prescribemeds';

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
    public addReportForm : FormGroup;
    public todaysDateDate : Date;
    public pharmacyId : Number;
    public workdays : WorkDay[];
    public showNoResults : boolean = false;

    

    @ViewChild(MatPaginator) paginator: MatPaginator;


    ngOnInit() {
      if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
        this.router.navigate(["/employee-welcome"]);
      }
   

      this.todaysDateDate = new Date();
      this.todaysDateDate.setDate(this.todaysDateDate.getDate() + 1);
      this.showSearchResultsForMedications = false;
      this.todaysDate = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
      this.employeeService.getScheduledAppointmentsForDate(Number(localStorage.getItem("userId")), this.todaysDate).subscribe(
        data => {
          this.appointments = data;
          for(let appointment of this.appointments) {
            this.showNoResults = false;
            appointment.startDateString = this.datePipe.transform(appointment.startTime, 'hh:mm');
            appointment.prescribedMedications = [];
            appointment.canAddPenalty = true;
            console.log(appointment.finished)
            this.pharmacyId = appointment.pharmacyId;
          }
          this.showSearchResults = false;
          this.searchAppointmentForm = new FormGroup({
            'date' : new FormControl(null, []),
          });
          this.searchMedicationsForm = new FormGroup({
            'name' : new FormControl("", [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
        
          })
          
          console.log(this.addReportForm)
          if (this.pharmacyId !== undefined) {
            this.employeeService.getUpcomingWorkDay(Number(localStorage.getItem("userId")), this.pharmacyId).subscribe(
              data => {
                this.workdays = data;
              }
            );

          } else {
            this.showNoResults = true;
          }

        }, 
        error => {
          alert(error);
        })

        this.addReportForm = new FormGroup({
          'details' : new FormControl(""),
      
        })

        
    }

    myFilter = (d: Date | null): boolean => {


      const day = (d || new Date()).getTime();
      for (let w of this.workdays) {
        let time = new Date(w.date).getTime();
        if (time === day) {
          return true;
        }
      }
      return false;
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
      report.reportNotes = a.reportNotes;
      report.appointmentId = a.id;
      this.employeeService.addReport(report).subscribe(
        data => {
              alert("Successfully added report!");
              a.finished = true;

            }, error => {
              alert(error)
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
      a.disabled = true;
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
          a.disabled = false;
          alert("Patient probably has another appointment.");
        }
      )
    }
    prescribeMedication(medication : MedicationsToRecommend, a : Appointment) {
      a.prescribedMedications.push(medication.name);
      let reservation = new CreaeteReservation();
      reservation.medicationId = medication.id;
      reservation.patientId = a.patientId;
      reservation.pharmacyId = a.pharmacyId;
      reservation.medicalStaffId = Number(localStorage.getItem("userId"));
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

      this.employeeService.prescribeMedication(reservation).subscribe(
        response => {
          
        }, error => {
          alert(error)
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
              a.disabled = false;
            
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
