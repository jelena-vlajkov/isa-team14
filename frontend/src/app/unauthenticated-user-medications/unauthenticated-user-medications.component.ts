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
  public showinfo : boolean;
  public selectedMedication : Medication;
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(public medicationService : MedicationService) { 
    this.showinfo=false;
  }

  public medications : Medication[] = new Array();

  ngOnInit(): void {
    this.getAllMedications();
  }
  showMoreInfo(m){
    console.log(m);
    this.showinfo = true;
    this.selectedMedication = m;
  }
  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }

}
