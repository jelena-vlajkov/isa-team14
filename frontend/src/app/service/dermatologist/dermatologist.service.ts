import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dermatologist } from '@app/model/dermatologist/dermatologist';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http : HttpClient) { }
  
  addDermatologist(data : Dermatologist) {
    return this.http.post(`${environment.baseUrl}/${environment.dermatologist}/${environment.add}`,data, {responseType : 'text'});
  }
}
