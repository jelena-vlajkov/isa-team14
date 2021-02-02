import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PharmacyRegistrationService {

  constructor(private http: HttpClient) { }
  registerPharmacy(data : Pharmacy){
    return this.http.post(`${environment.baseUrl}/${environment.pharmacy}/${environment.add}`,data, {responseType : 'text'});
  }
}
