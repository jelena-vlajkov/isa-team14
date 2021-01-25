import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dermatology-appointment',
  templateUrl: './dermatology-appointment.component.html',
  styleUrls: ['./dermatology-appointment.component.css']
})
export class DermatologyAppointmentComponent implements OnInit {
  dermatologist:String;
  date:String;
  time:String;
  duration:String;
  price:String;
  constructor() { }

  ngOnInit(): void {
    this.dermatologist="Marija Marijanovic";
    this.date="20,Jan 2020";
    this.time="4pm";
    this.duration="2h";
    this.price="20e";
  }

}
