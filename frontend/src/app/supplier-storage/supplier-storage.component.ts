import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Medication } from '@app/model/medications/medication';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { MedicationInStorage } from '@app/model/users/supplier/medicationInStorage';
import { NewMedicationToStorage } from '@app/model/users/supplier/newMedicationToStorage';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SupplierStorage } from '@app/model/users/supplier/supplierStorage';
import { MedicationService } from '@app/service/medication/medication.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-storage',
  templateUrl: './supplier-storage.component.html',
  styleUrls: ['./supplier-storage.component.css']
})
export class SupplierStorageComponent implements OnInit {

  public supplier : Supplier;
  public supplierStorage : SupplierStorage;
  public suppliersMedications : MedicationInStorage[];
  public displayedColumns: string[] = ['name', 'quantity', 'change'];
  public dataSource;
  public quantityForm : FormGroup;
  public editStorage : boolean;
  public addDrug: FormGroup;
  public medications : Medication[];
  public medicationToUpdate : MedicationInStorage;
  constructor(private medicationService : MedicationService,private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.loadSupplier();
    this.editStorage = false;
    this.addDrug = new FormGroup({
      'drug' : new FormControl(null, Validators.required),
      'amount' : new FormControl(null,  [Validators.required, Validators.pattern("^[0-9]*$")]),
    });
  }

  supplierLogout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);

  }

  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
      });
      this.supplierService.getSuppliersStorage(Number(localStorage.getItem('userId'))).subscribe(
        data=>{
          this.supplierStorage = data;
          this.dataSource = new MatTableDataSource(data.medicationInStorage);

        }
      );
      this.medicationService.findAllMedications().subscribe(
        data=>{
          this.medications = data;
        }
      );
  }
  confirmChange(element){
    var newquant = element.quantity;
    console.log(newquant);
    let updateItem = this.supplierStorage.medicationInStorage.find(this.findIndexToUpdate, element.name);
    
    let index = this.supplierStorage.medicationInStorage.indexOf(updateItem);

    var medinStorage = new MedicationInStorage(element.medication, newquant);

    this.supplierStorage.medicationInStorage[index] = medinStorage;

    console.log(this.supplierStorage);
    this.dataSource = this.supplierStorage.medicationInStorage;
    this.editStorage = false;
  }
  findIndexToUpdate(newItem) { 
    return newItem.name === this;
}

doesntContain(obj, list) {
  var i;
  for (i = 0; i < list.length; i++) {
      if (list[i].id === obj.id) {
          return false;
      }
  }

  return true;
}
  cancelEdit(element){
    this.quantityForm.reset();
    this.editStorage = false;
  }
  addnewDrug(){
    console.log(this.supplierStorage.medicationInStorage);
    var medinstor = new MedicationInStorage(this.addDrug.controls.drug.value, this.addDrug.controls.amount.value);
    // if(!this.dataSource.includes(medinstor)){
    console.log(medinstor);
    this.supplier
    var newMedicationToStorage = new NewMedicationToStorage(this.addDrug.controls.drug.value, this.addDrug.controls.amount.value, this.supplier);
    this.supplierService.addDrug(newMedicationToStorage).subscribe(
      res=>{
        this.addDrug.reset();
        this.loadSupplier();
        alert('Success');
      },
      error=>{
        alert("Cannot add - medication alredy present in storage");
      }
    )
  }

  edit(element){
    this.editStorage = true;
    this.medicationToUpdate = element;
    this.quantityForm = new FormGroup({
      'quantity' : new FormControl(element.quantity, [Validators.required, Validators.pattern("^[0-9]*$")]),
    });

  }
  updateDrug(){
    var newMedicationToStorage = new NewMedicationToStorage(this.medicationToUpdate.medication, this.quantityForm.controls.quantity.value, this.supplier);
    this.supplierService.editDrug(newMedicationToStorage).subscribe(
      res=>{
        this.quantityForm.reset();
        this.loadSupplier();
        alert('Success');
        this.editStorage= false;
      },
      error=>{
        alert("Failed!");
      }
    )
  }


}
