import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { NewMedicationToStorage } from '@app/model/users/supplier/newMedicationToStorage';
import { Supplier } from '@app/model/users/supplier/supplier'
import { SupplierStorage } from '@app/model/users/supplier/supplierStorage';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private http : HttpClient) { }

  registerSupplier(data : Supplier){
    return this.http.post(`${environment.baseUrl}/${environment.supplier}/${environment.add}`,data, {responseType : 'text'});
  }
  getSupplier(id:Number) : Observable<Supplier> {
    return this.http.get<Supplier>(`${environment.baseUrl}/${environment.supplier}/${environment.getById}?id=${id}`);
  }
  updateSupplier(data : Supplier){
    return this.http.post(`${environment.baseUrl}/${environment.supplier}/${environment.update}`,data, {responseType : 'text'});
  }  
  
  updatePassword(data : PasswordChanger){
    return this.http.post(`${environment.baseUrl}/${environment.supplier}/${environment.changepassword}`,data, {responseType : 'text'});
  }

  getSuppliersStorage(id : Number): Observable<SupplierStorage>{
    return this.http.get<SupplierStorage>(`${environment.baseUrl}/${environment.suppliersmedications}/${environment.findAll}?id=${id}`);
  }
  
  addDrug(data : NewMedicationToStorage){
    return this.http.post(`${environment.baseUrl}/${environment.suppliersmedications}/${environment.add}`,data, {responseType : 'text'})
  }

  editDrug(data : NewMedicationToStorage){
    return this.http.post(`${environment.baseUrl}/${environment.suppliersmedications}/${environment.update}`,data, {responseType : 'text'})
  }
}
