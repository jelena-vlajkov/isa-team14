import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { Observable } from 'rxjs';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  constructor(private http : HttpClient) {  }

  getAllPharmacies(): Observable<Pharmacy[]> {
    return this.http.get<Pharmacy[]>(`${environment.baseUrl}/pharmacy/getAll`);
  }
}
