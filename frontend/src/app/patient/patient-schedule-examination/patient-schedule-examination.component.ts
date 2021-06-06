import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import {Pharmacy} from '../../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import {Router} from '@angular/router'

@Component({
  selector: 'app-patient-schedule-examination',
  templateUrl: './patient-schedule-examination.component.html',
  styleUrls: ['./patient-schedule-examination.component.css']
})
export class PatientScheduleExaminationComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }

  constructor(private authenticationService : AuthenticationService, private pharmacyService : PharmacyService, private router : Router) { }

  public pharmacies : Pharmacy[] = new Array();
  public pharmacyId : string;

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

  choosePharmacy(id) {
    this.pharmacyId = id;
    console.log(this.pharmacyId)
    localStorage.setItem('pharmacyId', this.pharmacyId)
    this.router.navigate(['pharmacy-profile']);

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
        case 'grade': return compare(a.averageGradeCount, b.averageGradeCount, isAsc);   
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

