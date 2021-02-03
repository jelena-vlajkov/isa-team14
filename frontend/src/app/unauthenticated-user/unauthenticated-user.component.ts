import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import {Pharmacy} from '../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-unauthenticated-user',
  templateUrl: './unauthenticated-user.component.html',
  styleUrls: ['./unauthenticated-user.component.css']
})
export class UnauthenticatedUserComponent implements AfterViewInit {

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(private pharmacyService : PharmacyService, public medicationService : MedicationService) { 
    
  }
  public pharmacies : Pharmacy[] = new Array();
  public pharmaciesCopy : Pharmacy[] = new Array();
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

  getGradeValues(event) {

    console.log(event.value);
    if (event.value == 1){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.average_grade >= 0 &&
         p.average_grade <=1);
    }else if(event.value == 2){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.average_grade >= 1 &&
         p.average_grade <=2);
    }else if(event.value == 3){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.average_grade >= 2 &&
         p.average_grade <=3);
    }
    else if(event.value == 4){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.average_grade >= 3 &&
         p.average_grade <=4);
    } else if(event.value == 5){
      this.pharmacies = this.pharmaciesCopy;
      this.pharmacies = this.pharmacies.filter(p => p.average_grade >= 4 &&
         p.average_grade <=5);
    }else {
      this.pharmacies = this.pharmaciesCopy;
    }
  }

  getAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
        this.pharmaciesCopy = data;
      });
  }

  
  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
      });
  }

  findByName(inputName : String){
    this.pharmacyService.findByName(inputName).subscribe(data =>
      {
        this.pharmacies = data;
      })
  }

  findByAddress(inputAdr : String){
    this.pharmacyService.findByAddress(inputAdr).subscribe(data =>
      {
        this.pharmacies = data;
      })
  }

  sortData(sort: Sort){
    const data = this.pharmacies.slice();
    if (!sort.active || sort.direction === '') {
      this.pharmacies = data;
      return;
    }

    this.pharmacies = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return compare(a.average_grade, b.average_grade, isAsc);     
        default: return 0;
      }
     });
  }

  
}

function compare(a: Number | string, b: Number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
