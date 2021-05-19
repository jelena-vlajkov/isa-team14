import { Component, OnInit } from '@angular/core';
import {Ingredient} from '../../../model/medications/ingredient';
import {IngredientService} from '../../../service/medication/ingredients.service';
import {MedicalRecordService} from '../../../service/medicalRecord/medical-record.service';
import { AuthenticationService } from '../../../service/user/authentication.service';
import {MedicalRecord} from '../../../model/medicalRecord/medicalRecord';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-allergies',
  templateUrl: './edit-allergies.component.html',
  styleUrls: ['./edit-allergies.component.css']
})
export class EditAllergiesComponent implements OnInit {

  public allIngredients: Ingredient[] = new Array();
  public selectedAllergies: Ingredient[] = [];
  public medicalRecord : MedicalRecord;
  public ingredinets = new FormControl();

  constructor(private authenticationService: AuthenticationService, private ingredientService : IngredientService, private medicalRecordService : MedicalRecordService) { }

  ngOnInit(): void {
    this.ingredientService.findAllIngredients().subscribe(data =>
      {
        this.allIngredients = data;
        
      });

      this.medicalRecordService.getMedicalRecord(this.authenticationService.getUserValue().id).subscribe(
        data => {
          this.medicalRecord = data;
          console.log(this.medicalRecord)
        }
      )


  }

  comboChange(event) {
    if(!event) {
      console.log('dropdown is closed');
      this.selectedAllergies = this.ingredinets.value && this.ingredinets.value.toString();
      console.log(this.ingredinets.value);
    }
    
  }

  saveChanges(): void {
  

    this.medicalRecord.ingredients = [];
    this.medicalRecord.ingredients = this.ingredinets.value;
    
    this.medicalRecordService.addPatientIngredient(this.medicalRecord).subscribe(
      res=>{
        console.log("o1")
        alert('Success');
      },
      error=>{
        console.log("o2")
        alert("Fail")
      }
    ) 
   
  }

}
