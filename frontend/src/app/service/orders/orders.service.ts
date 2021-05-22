import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderedMedication } from '@app/model/medications/orderedMedication';
import { Order } from '@app/model/users/supplier/order';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {MedicationInOrder} from "@app/model/medicationOrder/medicationInOrder";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http : HttpClient) { }

  getAllUnfinishedOrders() :Observable<Order[]>{
    return this.http.get<Order[]>(`${environment.baseUrl}/${environment.order}/${environment.findAll}`);
  }
  getByUniqueId(id:number) : Observable<Order> {
    return this.http.get<Order>(`${environment.baseUrl}/${environment.order}/${environment.getByIdentifier}?id=${id}`);
   }
   getOrderedMedicationByIdentifier(id:number) : Observable<OrderedMedication[]> {
    return this.http.get<OrderedMedication[]>(`${environment.baseUrl}/${environment.order}/${environment.getOrderedMedicationByIdentifier}?id=${id}`);
   }
   getAllOrdersWehereOfferIsNotGivenBySupplier(id : Number):Observable<Order[]>{
    return this.http.get<Order[]>(`${environment.baseUrl}/${environment.order}/${environment.getAllOrdersWehereOfferIsNotGivenBySupplier}?id=${id}`);
  }


}
