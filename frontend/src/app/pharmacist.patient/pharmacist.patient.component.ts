import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Router} from "@angular/router";
import { AuthenticationService } from '../service/user/authentication.service'
import { PatientsOverview } from '@app/model/pharmderm/patientoverview';
import { EmployeeService } from '@app/service/employee/employee.service';
import {SearchParam} from '@app/model/pharmderm/searchparams'
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-pharmacy-patients',
  templateUrl: './pharmacist.patient.component.html',
  styleUrls: ['./pharmacist.patient.component.css']
})
export class PharmacistPatientsComponent implements OnInit {
  profileForm: FormGroup;
  changePasswordForm:FormGroup;
  name:String;
  surname:String;
  gender : String;
  selectedGender : String;

  address:String;
  phone:String;
  mail:String;
  password1:String;
  password2:String;

  oldpassword:String;

  public profile:boolean = true;
  public edit:boolean = false;
  public changePassword:boolean = false;
  public searchPatientsForm : FormGroup;
  public selectedValue : number;
  options = [
    { name: "Newest", value: 1 },
    { name: "Oldest", value: 2 },
    { name: "Name [A-Z]", value: 3 },
    { name: "Surname [A-Z]", value: 4 }
  ]

  public patients : PatientsOverview[];
  
  editProfileForm: FormGroup;

  constructor(private employeeService:EmployeeService,private router:Router, private authService: AuthenticationService, private datePipe : DatePipe) { }

  ngOnInit(): void {
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);
    }

    console.log(this.selectedValue);


    
  
    this.searchPatientsForm = new FormGroup({
      'name' : new FormControl("", []),
      'date' : new FormControl(null, []),
      "sort" : new FormControl()
    });


    this.employeeService.getAllPatientsByMedicalStaff(Number(localStorage.getItem('userId')), 'NONE').subscribe(
      result => {
        this.patients = result;
        console.log(this.patients)
        for (let p of this.patients) {
          console.log(p.upcomingAppointment)
          let date = this.datePipe.transform(p.dateOfBirth, 'dd.MM.yyyy.');
          p.dateOfBirthString = date;
          for (let app of p.previousAppointments) {
            let start = this.datePipe.transform(app.startTime, 'dd.MM.yyyy. hh:mm');
            let end = this.datePipe.transform(app.endTime, 'dd.MM.yyyy. hh:mm');
            app.endTimeString = end;
            app.startTimeString = start;

          }
        }
         }, 
         error => {
          alert(error)
         })
  }
  

  logout() {
    this.authService.logout();
  }

  isPharmacist() {
    let user = this.authService.currentUserValue;
    return user.role === 'Pharmacist'; 
  }
  searchPatients() {
    console.log("ASDFSADDS")
    let name = this.searchPatientsForm.controls.name.value;
    let date = this.searchPatientsForm.controls.date.value;
    console.log(date);

    if (date === null) {
      console.log("ASADASDASD")
    } 
    console.log(Number(localStorage.getItem('userId')))
    console.log(name);

    let searchParams = new SearchParam(name, date, Number(localStorage.getItem('userId')));

    if (this.selectedValue === 1) {
        searchParams.sortingType = 'DATE_DESC';
    }

    
    if (this.selectedValue === 2) {
      searchParams.sortingType = 'DATE_ASC';
    }

    
    if (this.selectedValue === 3) {
      searchParams.sortingType = 'NAME';
    }

    
    if (this.selectedValue === 4) {
      searchParams.sortingType = 'SURNAME';
    }
    console.log(searchParams.name)
    console.log(searchParams)
    this.employeeService.searchPatientsByParams(searchParams).subscribe(
      result => {
        
        this.patients = result;
        for (let p of this.patients) {
          let date = this.datePipe.transform(p.dateOfBirth, 'dd.MM.yyyy.');
          p.dateOfBirthString = date;
          for (let app of p.previousAppointments) {
            let start = this.datePipe.transform(app.startTime, 'dd.MM.yyyy. hh:mm');
            let end = this.datePipe.transform(app.endTime, 'dd.MM.yyyy. hh:mm');
            app.endTimeString = end;
            app.startTimeString = start;

          }
        }
         }, 
         error => {
          alert(error)
          this.employeeService.getAllPatientsByMedicalStaff(Number(localStorage.getItem('userId')), 'NONE').subscribe(
            result => {
              this.patients = result;
              for (let p of this.patients) {
                let date = this.datePipe.transform(p.dateOfBirth, 'dd.MM.yyyy.');
                p.dateOfBirthString = date;
                for (let app of p.previousAppointments) {
                  let start = this.datePipe.transform(app.startTime, 'dd.MM.yyyy. hh:mm');
                  let end = this.datePipe.transform(app.endTime, 'dd.MM.yyyy. hh:mm');
                  app.endTimeString = end;
                  app.startTimeString = start;
      
                }
               }
              },
               error => {
                alert(error)
               })
         })


  }

  startExamination(p : PatientsOverview) {
    this.router.navigate(['/appointment'], {state: {data: p.patientId}})
    
  }

  cancelSearch() {
    console.log(this.selectedValue);
    this.searchPatientsForm.controls.name.setValue("");
    this.employeeService.getAllPatientsByMedicalStaff(Number(localStorage.getItem('userId')), 'NONE').subscribe(
      result => {
        this.patients = result;
        for (let p of this.patients) {
          let date = this.datePipe.transform(p.dateOfBirth, 'dd.MM.yyyy.');
          p.dateOfBirthString = date;
          for (let app of p.previousAppointments) {
            let start = this.datePipe.transform(app.startTime, 'dd.MM.yyyy. hh:mm');
            let end = this.datePipe.transform(app.endTime, 'dd.MM.yyyy. hh:mm');
            app.endTimeString = end;
            app.startTimeString = start;

          }
        }
      }, 
         error => {
          alert(error)
         });
  }
}
