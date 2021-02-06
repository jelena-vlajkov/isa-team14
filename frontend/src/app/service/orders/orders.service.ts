import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '@app/model/users/supplier/order';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http : HttpClient) { }

  getAllUnfinishedOrders() :Observable<Order[]>{
    return this.http.get<Order[]>(`${environment.baseUrl}/${environment.order}/${environment.findAll}`);
  }
}
