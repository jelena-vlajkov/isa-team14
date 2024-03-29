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

  getPricelistByMedication(code : Number) :Observable<Pricelist[]>{
    return this.http.get<Pricelist[]>(`${environment.baseUrl}/${environment.pricelist}/${environment.getByMedication}?code=${code}`);
  }

  getPricelistByMedicationAndPharmacy(code : Number,pharmacyId:Number) :Observable<Pricelist>{
    return this.http.get<Pricelist>(`${environment.baseUrl}/${environment.pricelist}/${environment.getByMedicationAndPharmacy}?code=${code}&pharmacyId=${pharmacyId}`);
  }

  editPricelistEntity(pricelistEntity:Pricelist){
    return this.http.post(`${environment.baseUrl}/${environment.pricelist}/editPricelistEntity`,pricelistEntity);
  }

  addPricelistEntity(pricelistEntity:Pricelist):Observable<boolean>{
    return this.http.post<boolean>(`${environment.baseUrl}/${environment.pricelist}/${environment.addPricelistEntity}`,pricelistEntity);
  }

  deletePricelist(pricelistId:Number){
    return this.http.post(`${environment.baseUrl}/${environment.pricelist}/deletePricelistEntity`,pricelistId);
  }

  getAllByPharmacy(pharmacyId : Number) :Observable<Pricelist[]>{
    return this.http.get<Pricelist[]>(`${environment.baseUrl}/${environment.pricelist}/${environment.getAllByPharmacy}?pharmacyId=${pharmacyId}`);
  }
}
