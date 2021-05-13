import {AfterViewInit, Component, ViewChild, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Router } from '@angular/router';

import {AuthenticationService} from '../service/user/authentication.service'

@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharm.derm.medication.component.html',
  styleUrls: ['./pharm.derm.medication.component.css']
})
export class PharmDermMedicationsComponent {
  
    displayedColumns: string[] = ['position', 'name', 'dosage', '#'];
    dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
    constructor(private authService  : AuthenticationService, private router : Router) {}
    @ViewChild(MatPaginator) paginator: MatPaginator;
    
    ngOnInit() {
      if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
        this.router.navigate(["/employee-welcome"]);
  
      }
    }
    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
    }

    addMedication() {}
    logout() {
      this.authService.logout();
    }
  }
  
  export interface PeriodicElement {
    name: string;
    position: number;
  }
  
  const ELEMENT_DATA: PeriodicElement[] = [
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'},
    {position: 1, name: 'Brufen'}
  ];