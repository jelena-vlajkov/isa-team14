import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/user/authentication.service';
import {MedicalRecordService} from '../../service/medicalRecord/medical-record.service';
import { FormControl } from '@angular/forms';
import {Ingredient} from '../../model/medications/ingredient'

@Component({
  selector: 'app-allergies-dialog',
  templateUrl: './allergies-dialog.component.html',
  styleUrls: ['./allergies-dialog.component.css']
})
export class AllergiesDialogComponent implements OnInit {

  
  public ingredientsList: Ingredient[] = [];

  constructor(private authenticationService: AuthenticationService, private medicalRecordService: MedicalRecordService) { }

  ngOnInit(): void {
    this.medicalRecordService.getPatientIngredient(this.authenticationService.getUserValue().id).subscribe(
      data => {
        this.ingredientsList = data;
      }
    )
  }

}
