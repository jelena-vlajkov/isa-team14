import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pricelist} from "@app/model/pharmacy/pricelist";
import {environment} from "@environments/environment";
import {Promotion} from "@app/model/promotions/promotion";

@Injectable({
  providedIn: 'root'
})
export class PromotionsService {
  constructor(private http : HttpClient) { }

  getPromotionsByPharmacy(pharmacyId:Number) :Observable<Promotion[]>{
    return this.http.get<Promotion[]>(`${environment.baseUrl}/${environment.promotion}/${environment.getPromotionsByPharmacy}?pharmacyId=${pharmacyId}`);
  }

  addPromotion(promotion:Promotion){
    return this.http.post(`${environment.baseUrl}/${environment.promotion}/${environment.add}`, promotion);
  }
}
