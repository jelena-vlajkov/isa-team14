import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";
import {Observable} from "rxjs";
import {Ingredient} from "../../model/medications/ingredient";
import {MedicalRecord} from "../../model/medicalRecord/medicalRecord";
@Injectable({
  providedIn: 'root'
})
export class MedicalRecordService {

  constructor(private http : HttpClient) { }

  
  getPatientIngredient(id:Number) : Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${environment.baseUrl}/${environment.medicalRecord}/${environment.getPatientIngredients}/${id}`);
  }

  getMedicalRecord(id : Number ) : Observable<MedicalRecord> {
    return this.http.get<MedicalRecord>(`${environment.baseUrl}/${environment.medicalRecord}?patientId=${id}`)
  }

  addPatientIngredient(data : MedicalRecord) {
    return this.http.post(`${environment.baseUrl}/${environment.medicalRecord}/${environment.addPatientIngredient}`,data, {responseType : 'text'});
  }

}
