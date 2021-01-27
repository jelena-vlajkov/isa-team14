import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table'; 
import { IngredientService } from '../service/medication/ingredients.service';
import { Ingredient } from '../model/medications/ingredient';

@Component({
  selector: 'app-register-supplier',
  templateUrl: './register-supplier.component.html',
  styleUrls: ['./register-supplier.component.css']
})
export class RegisterSupplierComponent implements OnInit {
  public allIngredients : Ingredient[] = new Array();

  constructor(private ingredientService : IngredientService) { }

  ngOnInit(): void {
    this.loadAllMedications();
    console.log(this.allIngredients.length);
  }
  registerPharmacy(){

  }
  registerDermatologist(){

  }
  registerAdmin(){

  }
  registerSupplier(){

  }
  operationsWithDrugs(){

  }
  respondToComplaints(){

  }
  defineLoyalty(){
    
  }
  adminLogout(){
    
  }
  editProfile(){

  }
  addAdmin(){}

  
  loadAllMedications() {
    this.ingredientService.findAllIngredients().subscribe(data => 
      {
        this.allIngredients = data
      });
  }
}
