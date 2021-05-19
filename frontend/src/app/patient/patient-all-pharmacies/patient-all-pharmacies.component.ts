import { Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Pharmacy} from '../../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';


@Component({
  selector: 'app-patient-all-pharmacies',
  templateUrl: './patient-all-pharmacies.component.html',
  styleUrls: ['./patient-all-pharmacies.component.css']
})
export class PatientAllPharmaciesComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private pharmacyService : PharmacyService) { }

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  public pharmacies : Pharmacy[] = new Array();

  ngOnInit(): void {
    this.getAllPharmacies();
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

  getAllPharmacies(){
    this.pharmacyService.findAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
      });
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
        case 'name': return compare(a.name, b.name, isAsc);    
        case 'address': return compare(a.address.city.name, b.address.city.name, isAsc);    
        default: return 0;
      }
     });
  }

  
}

function compare(a: Number | String, b: Number | String, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}