import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dermatologist } from '@app/model/users/dermatologist/dermatologist';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http : HttpClient) { }

  addDermatologist(data : Dermatologist) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.add}`,data, {responseType : 'text'});
  }

  getDermatologistsToComplain(id : Number) :Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>(`${environment.baseUrl}/${environment.dermatologist}/${environment.getDermatologistToComplain}?id=${id}`);
  }

}
