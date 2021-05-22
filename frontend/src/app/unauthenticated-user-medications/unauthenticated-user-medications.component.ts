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
import { PricelistService } from '@app/service/pricelist/pricelist.service';
import { Pricelist } from '@app/model/pharmacy/pricelist';

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
  subMeds : String;
  ingredients : String;
  code : Number;
  grade : Number;
  dosage : Number;
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
  public drugType: DrugType;
  public drugTypes = Object.values(DrugType);
  private StringIsNumber = value => isNaN(Number(value)) === false;
  public ingredientsString : String ="";
  public subMedsString : String ="";
  public pricelistDataSource : Pricelist[];
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

  constructor(public pricelistService :PricelistService, public ingredientService:IngredientService, public dialog: MatDialog, private pharmacyService:PharmacyService, private supplierService : SupplierService, private systemAdmin : SysadminRegistrationService, private router: Router, private patientService: PatientService, public medicationService : MedicationService, private auth : AuthenticationService) {

  }


  ngOnInit(): void {
    this.getAllMedications();
    this.drugTypes = this.ToArray(DrugType);
    this.showinfo=false;
    if(this.isSupplier()){
      this.router.navigate(['/supplier']);
    }
    try{
      if(this.isPatient()){
          this.loadPatient();
        }else if (this.isAdmin()){
          this.loadAdmin();
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
  reserve(medication){
    alert('Colleague needs to implement reserving, so stay tuned until september :D');
  }

  findByName(inputName){
    if(inputName === ""){
      this.dataSource = this.medications;
    }else if(inputName.trim()===""){
      this.dataSource = this.medications;
    }else{
          this.medicationService.findByName(inputName).subscribe(data =>
      {
        this.dataSource = data;
      })
    }

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
  // showMoreInfo(m){
  //   console.log(m);
  //   this.showinfo = true;
  //   this.selectedMedication = m;
  //   console.log(m.ingredients);
  // }
  getPharmacies(medication){
    this.showinfo=true;
    console.log(medication);
    this.pharmacyService.getPharmacyByMedication(medication.code).subscribe(data =>
      {
        this.pharmacyDataSource = data;
        console.log(this.pharmacyDataSource);
      });  }

  getPricelists(medication){
    this.showinfo=true;
    this.pricelistService.getPricelistByMedication(medication.code).subscribe(data =>
      {
        this.pricelistDataSource = data;
        console.log(this.pricelistDataSource);
      });
  }
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
    const data = this.pricelistDataSource.slice();
    if (!sort.active || sort.direction === '') {
      this.pricelistDataSource = data;
      return;
    }

    this.pricelistDataSource = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return this.compare(a.pharmacy.averageGrade.count(), b.pharmacy.averageGrade.count(), isAsc);
        case 'name': return this.compare(a.pharmacy.name, b.pharmacy.name, isAsc);
        case 'price': return this.compare(a.price, b.price, isAsc);

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
  getForms(event){
    if(event.value==7){
      this.dataSource=this.medications;
    }else{
      this.medicationService.findByForm(event.value).subscribe(data =>
        {
          this.dataSource = data;
        });
  }
}
  getTypes(event) {
    if(event.value==8){
      this.dataSource=this.medications;
    }else{
      this.medicationService.findByType(event.value).subscribe(data =>
        {
          this.dataSource = data;
        });
    }

  }
  getKinds(event) {
    if(event.value==4){
      this.dataSource=this.medications;
    }else{
      this.medicationService.findByType(event.value).subscribe(data =>
        {
          this.dataSource = data;
        });
    }
  }
  getPrescribing(event) {
    if(event.value==2){
      this.dataSource=this.medications;
    }else{
      this.medicationService.findByPrescribing(event.value).subscribe(data =>
        {
          this.dataSource = data;
        });
    }
  }
  reset(){
    this.dataSource = this.medications;

  }
  ToArray(enumme) {
    return Object.keys(enumme)
        .filter(this.StringIsNumber)
        .map(key => enumme[key]);
  }

  openDialog(m): void {

    this.selectedMedication = m;

    // console.log(this.selectedMedication.ingredients);
    this.ingredientsString = "";
    try{
      this.ingredientsString = this.selectedMedication.ingredients[0].name;
      for(let i=1; i<this.selectedMedication.ingredients.length; i++){
      this.ingredientsString = this.ingredientsString +", "+ this.selectedMedication.ingredients[i].name;
    }
    }catch(error){

    }


    this.subMedsString ="";
    try {
      this.subMedsString = this.selectedMedication.substituteMedication[0].name;
      for(let i=1; i<this.selectedMedication.substituteMedication.length; i++){
      this.subMedsString = this.subMedsString +", "+ this.selectedMedication.substituteMedication[i].name;
     }
    console.log(this.subMedsString)
    } catch (error) {

    }

    const dialogRef = this.dialog.open(UnauthenticatedUserMedicationsComponentDialog, {
      width: '800px',
      height:'760px',
      data: {name: m.name, drugType: m.drugType, drugForm: m.drugForm, drugKind:  m.drugKind, typeOfPrescribing:m.typeOfPrescribing, contraindications : m.contraindications, additionalNotes: m.additionalNotes, dailyDose : m.dailyDose, dosage : m.dosage, grade : m.grade, code :m.code, ingredients : this.ingredientsString, subMeds : this.subMedsString}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }
}

@Component({
  selector: 'unauthenticated-user-medications-dialog',
  templateUrl: 'unauthenticated-user-medications-dialog.html',
})
export class UnauthenticatedUserMedicationsComponentDialog {

  constructor(
    public dialogRef: MatDialogRef<UnauthenticatedUserMedicationsComponentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onNoClick(): void {
    console.log(this.data.ingredients);
    this.dialogRef.close();
  }
  exportAsPDF(divId)
    {
        let data = document.getElementById(divId);
        html2canvas(data).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')
        let pdf = new jsPDF('p', 'cm', 'a4'); //Generates PDF in landscape mode
        // let pdf = new jspdf('p', 'cm', 'a4'); Generates PDF in portrait mode
        pdf.addImage(contentDataURL, 'PNG', 0, 0, 29.7, 21.0);
        pdf.save('PLSMAN.pdf');
      });
    }
}
