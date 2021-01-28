import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';

export interface Drug{
  name: string;
  company: string
}

export interface Pharm{
  name: string;
}

@Component({
  selector: 'app-patient-drug-reservation',
  templateUrl: './patient-drug-reservation.component.html',
  styleUrls: ['./patient-drug-reservation.component.css']
})
export class PatientDrugReservationComponent implements OnInit {
  drugFormGroup: FormGroup;
  pharmFormGroup: FormGroup;
  dateFormGroup: FormGroup;
  isEditable = true;
  isLinear = false;
  drugName = new FormControl('');
  pharmName = new FormControl('');
  drug: Drug;
  pharm: Pharm;

  displayedColumns : string[] = ['position', 'Drug', 'Company', '#'];
  displayedColumns2 : string[] = ['position', 'Pharmacy', '#'];
  
  drugs: Drug[] = [
    {name: 'Brufen',
    company: 'Galenika GAS'},
    {name: 'Aspirin',
    company: 'Hemo GAS'}
  ]

  pharms: Pharm[] = [{name: 'GALENIKA GAAS'}]

  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit() {
    this.drugFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.pharmFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.dateFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  pickDrug(drug){
    this.drugName.setValue(drug.name);
      }

      
  pickPharm(pharm){
    this.pharmName.setValue(pharm.name);
      }
}
