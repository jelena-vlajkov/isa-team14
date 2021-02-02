import { Component, OnInit } from '@angular/core';
import {Pharmacy} from '../model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';

@Component({
  selector: 'app-unauthenticated-user',
  templateUrl: './unauthenticated-user.component.html',
  styleUrls: ['./unauthenticated-user.component.css']
})
export class UnauthenticatedUserComponent implements OnInit {

  constructor(private pharmacyService : PharmacyService) { }
  public pharmacies : Pharmacy[] = new Array();
  pharmacy:boolean;
  medcs:boolean;

  ngOnInit(): void {
   
    this.pharmacy = false;
    this.medcs = false;
    
  }

  getAll(){
    this.pharmacyService.getAllPharmacies().subscribe(data =>
      {
        this.pharmacies = data;
      });
  }

  enablePharmacy(){
    this.pharmacy = true;
    this.medcs = false;
    this.getAll();
    console.log(this.pharmacies[0].name);
    console.log(this.pharmacies[0].average_grade);
  }

  enableMedcs(){
    this.pharmacy = false;
    this.medcs = true;
  }

}
