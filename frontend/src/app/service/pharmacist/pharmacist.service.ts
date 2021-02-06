import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  constructor(private http: HttpClient) { }

  getPharmacistsToComplain(id : Number) :Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getPharmacistToComplain}?id=${id}`);
  }
}
