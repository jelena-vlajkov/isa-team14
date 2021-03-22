import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offer } from '@app/model/users/supplier/offer';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  constructor(private http : HttpClient) { }

  getAllOffersBySuppllier(id : Number) :Observable<Offer[]>{
    return this.http.get<Offer[]>(`${environment.baseUrl}/${environment.offer}/${environment.findSuppliers}?id=${id}`);
  }

  updateOffer(data : Offer){
    return this.http.post(`${environment.baseUrl}/${environment.offer}/${environment.update}`,data, {responseType : 'text'});
  }
  
  giveOffer(data : Offer){
    return this.http.post(`${environment.baseUrl}/${environment.offer}/${environment.add}`,data, {responseType : 'text'});
  }
  findByStatus(status : Number, id : Number): Observable<Offer[]>{
    return this.http.get<Offer[]>(`${environment.baseUrl}/${environment.offer}/${environment.getByStatus}?id=${id}&status=${status}`);
  }
}
