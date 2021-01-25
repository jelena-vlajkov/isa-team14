import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-define-loyalty',
  templateUrl: './define-loyalty.component.html',
  styleUrls: ['./define-loyalty.component.css']
})
export class DefineLoyaltyComponent implements OnInit {
  discount: String;
  drugpoints:String;
  exampoints:String;
  counseling:String;

  constructor() { }

  ngOnInit(): void {
    this.discount = "20";
    this.drugpoints="22";
    this.exampoints="22";
    this.counseling="34";

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
}
