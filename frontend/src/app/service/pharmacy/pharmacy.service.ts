import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medication } from '@app/model/medications/medication';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { PharmacyAdmin } from '@app/model/users/pharmacyAdmin/pharmacyAdmin';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  constructor(private http: HttpClient) { }
  registerPharmacy(data : Pharmacy){
    return this.http.post(`${environment.baseUrl}/${environment.pharmacy}/${environment.add}`,data, {responseType : 'text'});
  }
  findAllPharmacies(): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/${environment.findAll}`);
  }
  registerPharmacyAdmin(data : PharmacyAdmin){
    return this.http.post(`${environment.baseUrl}/${environment.pharmacyAdmin}/${environment.add}`,data, {responseType : 'text'});
  }

  findByName(name : String) : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/getByName?name=${name}`);
  }

  findByAddress(add : String) : Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/getByAddress?address=${add}`);
  }
  
  getPharmacyToComplain(id : Number) :Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/${environment.getPharmacyToComplain}?id=${id}`);
  }

  getPharmacyByMedication(code : Number) :Observable<Pharmacy[]>{
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/${environment.getByMedication}?code=${code}`);
  }
  
}
