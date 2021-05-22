import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {User} from "@app/model/users";
import {UpdateEmployee} from "@app/model/pharmderm/UpdateEmployee"
import { Reservation } from '@app/model/pharmderm/reservations';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  constructor(private http:HttpClient) { }
  getPharmacistsByPharmacy(id : Number):Observable<User[]>{
    return this.http.get<User[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getByPharmacy}/?id=${id}`);}

  getPharmacistsToComplain(id : Number) :Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getPharmacistToComplain}?id=${id}`);
  }

  updateEmployee(employee : UpdateEmployee) : Observable<UpdateEmployee[]> {
    return this.http.post<UpdateEmployee[]>(`${environment.baseUrl}/${environment.updateEmployees}`, employee);
  }

  getReservationsByUniqueIdentifier(uniqueIdentifier : Number) : Observable<Reservation> {
    return this.http.get<Reservation>(`${environment.baseUrl}/${environment.reservations}/${environment.findReservations}?uniqueIdentifier=${uniqueIdentifier}`);
  }

  issueReservation(uniqueIdentifier : Number) : Observable<Reservation> {
    return this.http.get<Reservation>(`${environment.baseUrl}/${environment.reservations}/${environment.issueReservation}?uniqueIdentifier=${uniqueIdentifier}`);
  }

  registerPharmacist(pharmacist:Pharmacist) {
    return this.http.post(`${environment.baseUrl}/${environment.pharmacist}/${environment.registerPharmacist}`, pharmacist);
  }
  deletePharmacist(pharmacistId:Number) {
    return this.http.post(`${environment.baseUrl}/${environment.pharmacist}/${environment.deletePharmacist}?pharmacistId=${pharmacistId}`,null);
  }

}
