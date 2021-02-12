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
import { IngredientService } from '../service/medication/ingredients.service';
import { Ingredient } from '../model/medications/ingredient';
import { Observable } from 'rxjs';
import { AuthenticationService } from '@app/service/user';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { Allergy } from '@app/model/medications/allergy';
import { AllergyService } from '@app/service/medication/allergy.service';

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
  displayedColumns: string[] = ['id', 'name', 'pick'];

  public allMedications : Medication[] = new Array();
  public allIngredients : Ingredient[] = new Array();
  public allAllergies : Allergy[] = new Array();

  public name: string;
  private drugForm : DrugForm;
  private drugType: DrugType;
  private producer: string ;
  private typeOfPrescribing: TypeOfPrescribing;
  private additionalNotes: string;
  private contraindications: string;
  private dailyDose: number;
  private drugKind: DrugKind;
  private code:Number;

  registerMedication : FormGroup;
  newIngredient : FormGroup;
  public drugTypes = Object.values(DrugType);
  public drugKinds = Object.values(DrugKind);
  public drugForms = Object.values(DrugForm);
  prescribtion : TypeOfPrescribing;
  selectedMedications;
  selectedIngredients;
  selectedPrescribtion;
  medications = new FormControl();
  ingredients = new FormControl();
  allergies = new FormControl();

  private chosenIngredients : Ingredient[] = new Array();
  private newMedication;
  private subMeds : Number[] = new Array();
  private ings : Number[] = new Array();
  public chosenAllergies : Allergy[] = new Array();

  dataSource2 = new MatTableDataSource<Ingredient>();
  public dataSource;
  private StringIsNumber = value => isNaN(Number(value)) === false;
  filteredOptions: Observable<string[]>;
  ingredientControl = new FormControl();
  private sysAdmin : SystemAdmin;
  constructor(private allergyService : AllergyService,private router : Router ,private authenticationService : AuthenticationService,private medicationService : MedicationService, private ingredientService : IngredientService, private systemAdminService : SysadminRegistrationService) { }

  ngOnInit(): void {
    this.loadSystemAdmin();

    this.registerMedication = new FormGroup({
      'mname' : new FormControl(null, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'drugType' : new FormControl(null, Validators.required),
      'drugKind' : new FormControl(null, Validators.required),
      'drugForm' : new FormControl(null, Validators.required),
      'prescribtion': new FormControl(null, Validators.required),
      'daily' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'contra' : new FormControl(null, Validators.required),
      'notes' : new FormControl(null, Validators.required),
      'producer' : new FormControl(null, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'dosage' : new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),

    });
    this.newIngredient = new FormGroup({
      'name' : new FormControl(null ,[Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")])
    })
    this.drugTypes = this.ToArray(DrugType);
    this.drugKinds = this.ToArray(DrugKind);
    this.drugForms = this.ToArray(DrugForm);
    this.loadAllMedications();
    this.loadAllIngredients();
    this.loadAllAllergies();
  }
  loadSystemAdmin(){
    this.systemAdminService.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      {
        this.sysAdmin = new SystemAdmin(Number(localStorage.getItem('userId')), data.sysName, data.sysSurname, data.sysDateOfBirth, data.sysPhoneNumber, data.sysEmail, data.sysPassword, data.sysGender, data.sysAddress, data.sysRole, data.sysAuthorities, data.firstTimeChanged);
        if(!this.sysAdmin.firstTimeChanged){
          this.router.navigate(['/admin']);
        }
      });

  }
  
  registerDermatologist(){

  }
  registerIngredient(){
    var ingredient = new Ingredient(this.newIngredient.controls.name.value, this.chosenAllergies)
    this.ingredientService.addIngredient(ingredient).subscribe(
      res=>{
        this.newIngredient.reset();
        alert('Success - Added new ingredient');
        this.loadAllIngredients();
        this.chosenAllergies = new Array();
      },
      error=>{
        alert("Fail - Already added that ingredient")
      }
    )  
  }
  respondToComplaints(){

  }
  defineLoyalty(){
    
  }
  adminLogout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);

  }

  hashCode(str) {
    var hash = 0, i, chr;
    for (i = 0; i < str.length; i++) {
      chr   = str.charCodeAt(i);
      hash  = ((hash << 5) - hash) + chr;
      hash |= 0; // Convert to 32bit integer
    }
    var currdate = new Date();
    var currmillis = currdate.getMilliseconds();
    return hash + currmillis;;
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

    // for(let med of this.selectedMedications){
    //   this.subMeds.push(med.id);
    // }    
    
    // for(let ing of this.selectedIngredients){
    //   this.ings.push(ing.id);
    // }


    console.log(this.subMeds)
    this.newMedication = new Medication(this.name, this.drugForm, this.drugType, this.producer, this.prescribtion,this.contraindications, this.additionalNotes, this.dailyDose, this.drugKind, this.selectedMedications, this.hashCode(this.sysAdmin.sysEmail), this.chosenIngredients,0, this.registerMedication.controls.dosage.value);

    this.medicationService.addMedication(this.newMedication).subscribe(
      res=>{
        this.registerMedication.reset();
        alert('Success');
        this.router.navigate(['/admin']);
      },
      error=>{
        alert("Fail - Dug with that name, dosage, type, kind, form, producer and daily dose is already in system!")
      }
    )
  }

  loadAllIngredients(){
    this.ingredientService.findAllIngredients().subscribe(data=>
      {
        this.allIngredients = data;
        this.dataSource = new MatTableDataSource(data);

      });
  }
      
  loadAllMedications() {
    this.medicationService.findAllMedications().subscribe(data => 
      {
        this.allMedications = data
      });
  }
  loadAllAllergies() {
    this.allergyService.findAllAllergies().subscribe(data => 
      {
        this.allAllergies = data
      });
  }
  pickIngredient(ingredient : Ingredient){
    if(!this.chosenIngredients.find(x => x === ingredient)){
      this.chosenIngredients.push(ingredient);
      this.dataSource2.data = this.chosenIngredients;
    }
  }
  removeIngredient(ingredient: Ingredient){
    this.chosenIngredients = this.chosenIngredients.filter(obj => obj !== ingredient);
    this.dataSource2.data = this.chosenIngredients;
  }
  applyFilter(filterValue: string){
    this.dataSource.filter = filterValue.trim().toLowerCase();
}

  ToArray(enumme) {
    return Object.keys(enumme)
        .filter(this.StringIsNumber)
        .map(key => enumme[key]);
  }
  // private _filter(value: string): string[] {
  //   const filterValue = value.toLowerCase();

  //   return this.allIngredients.map[key].filter(option => option.toLowerCase().includes(filterValue));
  // }
}
