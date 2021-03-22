import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pricelist } from '@app/model/pharmacy/pricelist';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private http : HttpClient) { }
  getPharmacyByMedication(code : Number) :Observable<Pricelist[]>{
    return this.http.get<Pricelist[]>(`${environment.baseUrl}/${environment.pricelist}/${environment.getByMedication}?code=${code}`);
  }
}
