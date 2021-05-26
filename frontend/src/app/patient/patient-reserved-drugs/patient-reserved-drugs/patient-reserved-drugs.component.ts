import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import {MatSortModule} from '@angular/material/sort';



@Component({
  selector: 'app-patient-reserved-drugs',
  templateUrl: './patient-reserved-drugs.component.html',
  styleUrls: ['./patient-reserved-drugs.component.css']
})
export class PatientReservedDrugsComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  ngOnInit(): void {
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

}
