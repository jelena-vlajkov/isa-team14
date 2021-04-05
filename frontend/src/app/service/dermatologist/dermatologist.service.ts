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

  getDermatologistsByPharmacy(id:Number):Observable<User[]> {
    return this.http.get<User[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getByPharmacy}/?id=${id}`);
  }
  addDermatologist(data : Dermatologist) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.add}`,data, {responseType : 'text'});
  }

  getDermatologistsToComplain(id : Number) :Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getDermatologistToComplain}?id=${id}`);
  }

}
