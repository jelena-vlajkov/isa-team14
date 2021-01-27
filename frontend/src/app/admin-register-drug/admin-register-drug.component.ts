import { Component, Inject, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { DrugForm } from '../model/medications/drugForm';
import { DrugKind } from '../model/medications/drugKind';
import { DrugType } from '../model/medications/drugType';
import { TypeOfPrescribing } from '../model/medications/typeOfPrescribing';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { Medication } from '../model/medications/medication';
import { MedicationService } from '../service/medication/medication.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-admin-register-drug',
  templateUrl: './admin-register-drug.component.html',
  styleUrls: ['./admin-register-drug.component.css']
})
export class AdminRegisterDrugComponent implements OnInit {
  public allMedications : Medication[] = new Array();

  public name: string;
  private drugForm : DrugForm;
  private drugType: DrugType;
  private producer: string ;
  private typeOfPrescribing: TypeOfPrescribing;
  private additionalNotes: string;
  private contraindications: string;
  private dailyDose: number;
  private drugKind: DrugKind;
  private code:string;

  registerMedication : FormGroup;
  public drugTypes = Object.values(DrugType);
  public drugKinds = Object.values(DrugKind);
  public drugForms = Object.values(DrugForm);
  prescribtion : TypeOfPrescribing;
  selectedMedications;
  selectedPrescribtion;
  medications = new FormControl();
  private newMedication;
  private subMeds : Number[] = new Array();
  private StringIsNumber = value => isNaN(Number(value)) === false;

  constructor(private medicationService : MedicationService) { }

  ngOnInit(): void {

    this.registerMedication = new FormGroup({
      'mname' : new FormControl(null, Validators.required),
      'code' : new FormControl(null, Validators.required),
      'drugType' : new FormControl(null, Validators.required),
      'drugKind' : new FormControl(null, Validators.required),
      'drugForm' : new FormControl(null, Validators.required),
      'prescribtion': new FormControl(null, Validators.required),
      'daily' : new FormControl(null, Validators.required),
      'contra' : new FormControl(null, Validators.required),
      'notes' : new FormControl(null, Validators.required),
      'producer' : new FormControl(null, Validators.required)
    });
    this.drugTypes = this.ToArray(DrugType);
    this.drugKinds = this.ToArray(DrugKind);
    this.drugForms = this.ToArray(DrugForm);
    this.loadAllMedications();
  }
  // openSubMedicineDialog(): void {
  //   const dialogRef = this.dialog.open(SubstituteDrugDialog, {
  //     width: '450px',
  //     data: {name: this.name, animal: this.animal}
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //     this.animal = result;
  //   });
  // }


  
  registerDermatologist(){

  }
  
  operationsWithDrugs(){

  }
  respondToComplaints(){

  }
  defineLoyalty(){
    
  }
  adminLogout(){
    
  }


  addDrug(){
    this.name = this.registerMedication.value.mname;
    this.code = this.registerMedication.value.code;
    this.drugType = this.registerMedication.value.drugType;
    this.drugKind = this.registerMedication.value.drugKind;
    this.drugForm = this.registerMedication.value.drugForm;
    this.additionalNotes = this.registerMedication.value.notes;
    this.prescribtion = this.selectedPrescribtion;
    this.contraindications = this.registerMedication.value.contra;
    this.dailyDose = this.registerMedication.value.daily;
    this.producer = this.registerMedication.value.producer;
    this.subMeds = new Array();
    for(let med of this.selectedMedications){
      this.subMeds.push(med.id);
    }
    console.log(this.subMeds)
    this.newMedication = new Medication(this.name, this.drugForm, this.drugType, this.producer, this.prescribtion,this.contraindications, this.additionalNotes, this.dailyDose, this.drugKind, this.subMeds);

    this.medicationService.addMedication(this.newMedication).subscribe(
      res=>{
        this.registerMedication.reset();
        alert('Success');
      },
      error=>{
        alert("Fail")
      }
    )
  }

      
  loadAllMedications() {
    this.medicationService.findAllMedications().subscribe(data => 
      {
        this.allMedications = data
      });
  }
  
  ToArray(enumme) {
    return Object.keys(enumme)
        .filter(this.StringIsNumber)
        .map(key => enumme[key]);
  }
}
