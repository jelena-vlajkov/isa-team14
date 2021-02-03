import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Supplier } from '@app/model/users/supplier/supplier'
import { environment } from '@environments/environment';
@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private http : HttpClient) { }

  registerSupplier(data : Supplier){
    return this.http.post(`${environment.baseUrl}/${environment.supplier}/${environment.add}`,data, {responseType : 'text'});
  }
  
}
