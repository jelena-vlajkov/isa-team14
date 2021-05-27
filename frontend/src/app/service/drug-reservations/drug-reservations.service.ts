import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DrugReservationsService {

  constructor(private http : HttpClient) { }

  isDrugReserved(medicationId:Number,pharmacyId:Number):Observable<boolean> {
    return this.http.get<boolean>(`${environment.baseUrl}/${environment.reservations}/${environment.isDrugReserved}/?medicationId=${medicationId}&pharmacyId=${pharmacyId}`);
  }
}
