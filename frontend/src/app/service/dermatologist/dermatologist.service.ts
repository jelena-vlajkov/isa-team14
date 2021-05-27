import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "@app/model/users/user";
import { Dermatologist } from '@app/model/users/dermatologist/dermatologist';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http : HttpClient) { }

  getDermatologistsByPharmacy(id:Number):Observable<Dermatologist[]> {
    return this.http.get<Dermatologist[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getByPharmacy}/?id=${id}`);
  }
  addDermatologist(data : Dermatologist) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.add}`,data, {responseType : 'text'});
  }

  getDermatologistsToComplain(id : Number) :Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getDermatologistToComplain}?id=${id}`);
  }
  deleteDermatologistFromPharmacy(dermatologistId:Number,pharmacyId:Number) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.deleteDermatologistFromPharmacy}/?pharmacyId=${pharmacyId}&dermatologistId=${dermatologistId}`,null);
  }
  getDermatologistsNotInPharmacy(pharmacyId:Number):Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getDermatologistNotInPharmacy}/?pharmacyId=${pharmacyId}`);
  }

  addDermatologistToPharmacy(pharmacyId:Number,dermatologistId:Number) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.addDermatologistToPharmacy}/?pharmacyId=${pharmacyId}&dermatologistId=${dermatologistId}`,null);
  }

}
