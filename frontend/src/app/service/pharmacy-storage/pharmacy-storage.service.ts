import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {environment} from "@environments/environment";
import {HttpClient} from "@angular/common/http";
import {medicationInStorage} from "@app/model/pharmacyStorage/medicationInStorage";


@Injectable({
  providedIn: 'root'
})
export class PharmacyStorageService {

  constructor(private http: HttpClient) { }

  getByPharmacy(id : Number) :Observable<medicationInStorage[]>{
    return this.http.get<medicationInStorage[]>(`${environment.baseUrl}/${environment.pharmacyStorage}/${environment.getMedicationsInPharmacy}?ph=${id}`);
  }
}
