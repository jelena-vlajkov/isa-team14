import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharm.derm.medication.component.html',
  styleUrls: ['./pharm.derm.medication.component.css']
})
export class PharmDermMedicationsComponent {
  
    displayedColumns: string[] = ['position', 'name', 'dosage', '#'];
    dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  
    @ViewChild(MatPaginator) paginator: MatPaginator;
  
    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
    }

    addMedication() {}

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