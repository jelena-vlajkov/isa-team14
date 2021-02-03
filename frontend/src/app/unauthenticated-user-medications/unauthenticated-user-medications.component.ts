import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';


@Component({
  selector: 'app-unauthenticated-user-medications',
  templateUrl: './unauthenticated-user-medications.component.html',
  styleUrls: ['./unauthenticated-user-medications.component.css']
})
export class UnauthenticatedUserMedicationsComponent implements OnInit {

  
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(public medicationService : MedicationService) { 
    
  }

  public medications : Medication[] = new Array();

  ngOnInit(): void {
    this.getAllMedications();
  }

  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }

}
