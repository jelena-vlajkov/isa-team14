import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {environment} from "@environments/environment";
import {HttpClient} from "@angular/common/http";
import {medicationInStorage} from "@app/model/pharmacyStorage/medicationInStorage";
import {PharmacyStorage} from "@app/model/pharmacyStorage/pharmacyStorage";
import {Medication} from "@app/model/medications/medication";


@Injectable({
  providedIn: 'root'
})
export class PharmacyStorageService {

  constructor(private http: HttpClient) { }

  getByPharmacy(id : Number) :Observable<PharmacyStorage[]>{
    return this.http.get<PharmacyStorage[]>(`${environment.baseUrl}/${environment.pharmacyStorage}/${environment.getMedicationsInPharmacy}?ph=${id}`);
  }

  getMedicationsInPharmacy(pharmacyId: Number) :Observable<Medication[]>{
    return this.http.get<Medication[]>(`${environment.baseUrl}/${environment.pharmacyStorage}/${environment.getMedicationsByPharmacy}?ph=${pharmacyId}`);
  }
}
