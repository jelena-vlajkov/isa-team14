import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-unauthenticated-user',
  templateUrl: './unauthenticated-user.component.html',
  styleUrls: ['./unauthenticated-user.component.css']
})
export class UnauthenticatedUserComponent implements OnInit {

  constructor() { }
  pharmacy:boolean;
  medcs:boolean;

  ngOnInit(): void {
    this.pharmacy = false;
    this.medcs = false;
  }

  enablePharmacy(){
    this.pharmacy = true;
    this.medcs = false;
  }

  enableMedcs(){
    this.pharmacy = false;
    this.medcs = true;
  }

}
