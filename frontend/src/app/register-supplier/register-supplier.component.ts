import { Component, OnInit } from '@angular/core';
import { Medication } from '../model/medications/medication';
import { MedicationService } from '../service/medication/medication.service';
import { MatTableModule } from '@angular/material/table'; 

@Component({
  selector: 'app-register-supplier',
  templateUrl: './register-supplier.component.html',
  styleUrls: ['./register-supplier.component.css']
})
export class RegisterSupplierComponent implements OnInit {
  public allMedications : Medication[] = new Array();

  constructor(private medicationService : MedicationService) { }

  ngOnInit(): void {
    this.loadAllMedications();
    console.log(this.allMedications.length);
  }
  registerPharmacy(){

  }
  registerDermatologist(){

  }
  registerAdmin(){

  }
  registerSupplier(){

  }
  operationsWithDrugs(){

  }
  respondToComplaints(){

  }
  defineLoyalty(){
    
  }
  adminLogout(){
    
  }
  editProfile(){

  }
  addAdmin(){}

  
  loadAllMedications() {
    this.medicationService.findAllMedications().subscribe(data => 
      {
        this.allMedications = data
      });
  }
}
