import { Component, OnInit } from '@angular/core';
import {Pharmacy} from '../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';


@Component({
  selector: 'app-unauthenticated-user',
  templateUrl: './unauthenticated-user.component.html',
  styleUrls: ['./unauthenticated-user.component.css']
})
export class UnauthenticatedUserComponent implements OnInit {

  constructor(private pharmacyService : PharmacyService, public medicationService : MedicationService) { }
  public pharmacies : Pharmacy[] = new Array();
  public medications : Medication[] = new Array();
  pharmacy:boolean;
  medcs:boolean;

  ngOnInit(): void {
   
    this.pharmacy = false;
    this.medcs = false;
    
  }

  enablePharmacy(){
    this.pharmacy = true;
    this.medcs = false;

    this.getAllPharmacies();
  }

  enableMedcs(){
    this.pharmacy = false;
    this.medcs = true;

    this.getAllMedications();

  }

  getAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
      });
  }

  
  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }
}
