import { Injectable } from '@angular/core';
import {Supplier} from "@app/model/users/supplier/supplier";
import {environment} from "@environments/environment";
import {Order} from "@app/model/medicationOrder/order";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http : HttpClient) { }
  addOrder(data : Order){
    return this.http.post(`${environment.baseUrl}/${environment.order}/addOrder`,data, {responseType : 'text'});
  }
}
