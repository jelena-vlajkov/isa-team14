import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

import {AuthenticationService} from '../service/user/authentication.service'

@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharm.derm.medication.component.html',
  styleUrls: ['./pharm.derm.medication.component.css']
})
export class PharmDermMedicationsComponent {
  
    displayedColumns: string[] = ['position', 'name', 'dosage', '#'];
    dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
    constructor(private authService  : AuthenticationService) {}
    @ViewChild(MatPaginator) paginator: MatPaginator;
  
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