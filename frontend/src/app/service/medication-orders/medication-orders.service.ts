import { Injectable } from '@angular/core';
import {environment} from "@environments/environment";
import {MedicationInOrder} from "@app/model/medicationOrder/medicationInOrder";
import {HttpClient} from "@angular/common/http";
import {Order} from "@app/model/medicationOrder/order";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MedicationOrdersService {

  constructor(private http:HttpClient) { }
  addOrder(data :Order):Observable<Order> {
    return this.http.post<Order>(`${environment.baseUrl}/${environment.order}/addOrder`,data);
  }

  addMedicationInOrder(medicationInOrder :MedicationInOrder[]){
    return this.http.post(`${environment.baseUrl}/${environment.medicationInOrder}/${environment.addMedicationInOrder}`,medicationInOrder);
  }

  getOrdersByPharmacy(pharmacyId:Number):Observable<Order[]>{
    return this.http.get<Order[]>(`${environment.baseUrl}/${environment.order}/${environment.getOrdersByPharmacy}?pharmacyId=${pharmacyId}`);
  }
  findById(id:Number) : Observable<Order> {
    return this.http.get<Order>(`${environment.baseUrl}/${environment.order}/${environment.findById}?id=${id}`);
  }
}
