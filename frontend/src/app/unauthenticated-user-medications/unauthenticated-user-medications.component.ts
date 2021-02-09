import { Component, OnInit, AfterViewInit, ViewChild, Inject} from '@angular/core';
import {Medication} from "../model/medications/medication"
import { MedicationService } from '@app/service/medication/medication.service';
import {Sort} from '@angular/material/sort';
import {MatSort} from '@angular/material/sort';
import { Patient } from '@app/model/users/patient/patient';
import { PatientService } from '@app/service/patient/patient.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/service/user';
import { Role } from '@app/model/users';
import { SysadminRegistrationService } from '@app/service/sysadmin-registration/sysadmin-registration.service';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyService } from '@app/service/pharmacy/pharmacy.service';
import { DrugType } from '@app/model/medications/drugType';
import html2canvas from 'html2canvas';
import {jsPDF} from 'jspdf';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { TypeOfPrescribing } from '@app/model/medications/typeOfPrescribing';
import { DrugForm } from '@app/model/medications/drugForm';
import { DrugKind } from '@app/model/medications/drugKind';
import { Ingredient } from '@app/model/medications/ingredient';
import { CommonModule } from "@angular/common";
import { BrowserModule } from '@angular/platform-browser'
import { IngredientService } from '@app/service/medication/ingredients.service';

export interface DialogData {
  name: String;
  producer : String; 
  drugType: DrugType;
  drugKind : DrugKind;
  drugForm : DrugForm; 
  typeOfPrescribing : TypeOfPrescribing;
  contraindications : String;
  additionalNotes : String;
  dailyDose : String;
  substituteMedications : Medication[];
  ingredients : Ingredient[];
  code : Number;
  grade : Number;
} 

@Component({
  selector: 'app-unauthenticated-user-medications',
  templateUrl: './unauthenticated-user-medications.component.html',
  styleUrls: ['./unauthenticated-user-medications.component.css']
})

export class UnauthenticatedUserMedicationsComponent implements OnInit {
  public showinfo : boolean;
  public selectedMedication : Medication;
  public patient : Patient;
  public patientHere : boolean = false;
  public noone : boolean = true;
  public sysAdmin : SystemAdmin;
  public supplier : Supplier;
  public drugName : String;
  private drugType: DrugType;
  public drugTypes = Object.values(DrugType);
  private StringIsNumber = value => isNaN(Number(value)) === false;

  public pharmacyDataSource : Pharmacy[];
  public dataSource : Medication[];
  public medications : Medication[] = new Array();
  public allIngredients : Ingredient[] = new Array();

  public medsIngs : Ingredient[] = new Array();

  @ViewChild(MatSort) sort: MatSort;
  
  ngAfterViewInit() {
  }

  animal: string;
  name: string;

  constructor(public ingredientService:IngredientService, public dialog: MatDialog, private pharmacyService:PharmacyService, private supplierService : SupplierService, private systemAdmin : SysadminRegistrationService, private router: Router, private patientService: PatientService, public medicationService : MedicationService, private auth : AuthenticationService) { 
    this.showinfo=false;
  }


  ngOnInit(): void {
    this.getAllMedications();
    this.drugTypes = this.ToArray(DrugType);

    try{
      if(this.isPatient()){
          this.loadPatient();
        }else if (this.isAdmin()){
          this.loadAdmin();
        }else if(this.isSupplier()){
          this.loadSupplier();
        }    
      
    }catch(error){
      console.log('UPUCACU SE VISE AAAAAAAA');
      console.log('ok radi sve kul idegasnamax');
    }

  }
  

  checkLoggedInUser(){
    return this.auth.getUserValue();
  }

  isPatient() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Patient;
  }
  isAdmin() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.SysAdmin;
  }
  isSupplier() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Supplier;
  }

  findByName(inputName){
    this.medicationService.findByName(inputName).subscribe(data =>
      {
        this.dataSource = data;
      })
  }

  logout(){
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  loadPatient(){
    this.patientService.getPatientById(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.patient = data;
      });
  }

  loadAdmin(){
    this.systemAdmin.getSysAdmin(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.sysAdmin = data;
      });
  }
  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(data =>
      {
        this.supplier = data;
      });
  }
  showMoreInfo(m){
    console.log(m);
    this.showinfo = true;
    this.selectedMedication = m;
    console.log(m.ingredients);
  }
  getPharmacies(medication){
    this.showinfo=true;
    console.log(medication);
    this.pharmacyService.getPharmacyByMedication(medication.code).subscribe(data =>
      {
        this.pharmacyDataSource = data;
        console.log(this.pharmacyDataSource);
      });  }
  getAllMedications(){
    this.medicationService.findAllMedications().subscribe(data =>
      {
        this.medications = data;
        this.dataSource = data;
      });
  }


  sortData(sort: Sort){
    const data = this.dataSource.slice();
    if (!sort.active || sort.direction === '') {
      this.dataSource = data;
      return;
    }

    this.dataSource = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return this.compare(a.grade, b.grade, isAsc);
        case 'producer': return this.compare(a.producer, b.producer, isAsc);     
        case 'type': return this.compare(a.drugType, b.drugType, isAsc);     
        case 'name': return this.compare(a.name, b.name, isAsc);     

        default: return 0;
      }
     });
  }
  sortDataPharmacy(sort: Sort){
    const data = this.pharmacyDataSource.slice();
    if (!sort.active || sort.direction === '') {
      this.pharmacyDataSource = data;
      return;
    }

    this.pharmacyDataSource = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return this.compare(a.average_grade, b.average_grade, isAsc);
        case 'name': return this.compare(a.name, b.name, isAsc);     

        default: return 0;
      }
     });
  }

  compare(a: Number | String, b: Number | String, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  getGradeValues(event) {

    console.log(event.value);
    if (event.value == 1){
      this.dataSource = this.medications.filter(p => p.grade >= 0 &&
         p.grade <=1);
    }else if(event.value == 2){
      this.dataSource = this.medications.filter(p => p.grade >= 1 &&
         p.grade <=2);
    }else if(event.value == 3){
      this.dataSource = this.medications.filter(p => p.grade >= 2 &&
         p.grade <=3);
    }
    else if(event.value == 4){
      this.dataSource = this.medications.filter(p => p.grade >= 3 &&
         p.grade <=4);
    } else if(event.value == 5){
      this.dataSource = this.medications.filter(p => p.grade >= 4 &&
         p.grade <=5);
    }else {
      this.dataSource = this.medications;
    }
  }
  getTypes(event) {

    console.log(event);
    if (event.value == 0){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.HERBAL);
    }else if(event.value == 1){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.BIOLOGICAL);
    }else if(event.value == 2){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.HOMEOPATIC);
    }else if(event.value == 3){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.HUMAN);
    } else if(event.value == 4){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.BLOOD);
    }else if(event.value == 5){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.RADIOPHARAMEUTICALS);
    }else if(event.value == 6){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.TRADITIONALHERBAL);
    }else if(event.value == 7){
      this.dataSource = this.medications.filter(p => p.drugType === DrugType.VACCINES);
    }
    else {
      this.dataSource = this.medications;
    }
  }
  exportAsPDF(divId)
    {
        let data = document.getElementById('plswork');  
        html2canvas(data).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')  
        let pdf = new jsPDF('l', 'cm', 'a4'); //Generates PDF in landscape mode
        // let pdf = new jspdf('p', 'cm', 'a4'); Generates PDF in portrait mode
        pdf.addImage(contentDataURL, 'PNG', 0, 0, 29.7, 21.0);  
        pdf.save('PLSMAN.pdf');   
      }); 
    }
  ToArray(enumme) {
    return Object.keys(enumme)
        .filter(this.StringIsNumber)
        .map(key => enumme[key]);
  }

  openDialog(m): void {
    // this.ingredientService.findAllIngredients().subscribe(data=>{this.allIngredients = data;});
    // var medsIngrs : Ingredient[] = new Array();
    // for(let i=0; i<m.ingredients; i++){
    //   for(let j=0; j<this.allIngredients.length; j++){
    //     if(m.ingredients[i])
    //   }
    // }
    

    const dialogRef = this.dialog.open(UnauthenticatedUserMedicationsComponentDialog, {
      width: '800px',
      height:'600px',
      data: {name: m.name, drugType: m.drugType, drugForm: m.drugForm, drugKind:  m.drugKind, typeOfPrescribing:m.typeOfPrescribing, contraindications : m.contraindications, additionalNotes: m.additionalNotes, dailyDose : m.dailyDose, grade : m.grade, code :m.code, ingredients : m.ingredients, subMeds : m.substituteMedication}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }
}

@Component({
  selector: 'unauthenticated-user-medications-dialog',
  templateUrl: './unauthenticated-user-medications-dialog.html',
})
export class UnauthenticatedUserMedicationsComponentDialog {

  constructor(
    public dialogRef: MatDialogRef<UnauthenticatedUserMedicationsComponentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}