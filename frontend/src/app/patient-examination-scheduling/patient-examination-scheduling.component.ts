import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';


export interface Examination{
  price: number;
  grade: number;
  date: string;
  dermatologist: string;
  time: string;
}

@Component({
  selector: 'app-patient-examination-scheduling',
  templateUrl: './patient-examination-scheduling.component.html',
  styleUrls: ['./patient-examination-scheduling.component.css']
})
export class PatientExaminationSchedulingComponent implements AfterViewInit {


  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
  }

  timeh = new Date().getHours();
  timem = new Date().getMinutes();

  examinations: Examination[]= [
    {date: new Date().toDateString(),
    time: this.timeh + ":" + this.timem, 
    dermatologist: 'Jelena Vlajkov', 
    price: 1200, 
    grade: 5 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + '40', 
      dermatologist: 'Jelena Vlajkov', 
      price: 1200, 
    grade: 5 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 1300, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 15500, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 15500, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 15500, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 15500, 
    grade: 10 },
    {date: new Date().toDateString(),
      time: this.timeh + ":" + this.timem, 
      dermatologist: 'Jelena Vlajkov', 
      price: 15500, 
    grade: 10 }
  ];

  sortedData: Examination[];

  constructor() { 
    this.sortedData = this.examinations.slice();
  }

  sortData(sort: Sort) {
    const data = this.examinations.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'price': return compare(a.price, b.price, isAsc);
        case 'grade': return compare(a.grade, b.grade, isAsc);     
        default: return 0;
      }
     });
  }


}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
