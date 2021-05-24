import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { PatientService} from '../../../service/patient/patient.service';
import {MatSortModule} from '@angular/material/sort';
import { PrescribedEdrugs  } from '../../../model/medications/prescibedEDrugs';

@Component({
  selector: 'app-patient-issued-e-drugs',
  templateUrl: './patient-issued-e-drugs.component.html',
  styleUrls: ['./patient-issued-e-drugs.component.css']
})
export class PatientIssuedEDrugsComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  public eDrugs : PrescribedEdrugs[] = new Array();


  ngOnInit(): void {
    this.patientService.getAllPrescribedDrugForPatient(this.authenticationService.getUserValue().id).subscribe(
      data =>
      {
        this.eDrugs = data;
      }, 
      err => {
        alert('There is issued drugs by ePrescription')
      }
      );
  }

  patientLogOut(){
    this.authenticationService.logout();
  }
}
