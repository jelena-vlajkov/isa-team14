import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "@app/models";
import { Dermatologist } from '@app/model/users/dermatologist/dermatologist';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http : HttpClient) { }

  getDermatologistByPharmacy(id:Number):Observable<User[]> {
    return this.http.get<User[]>(`${environment.baseUrl}/dermatologist/getByPharmacy/?id=${id}`);
  }
  addDermatologist(data : Dermatologist) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.add}`,data, {responseType : 'text'});
  }
}
