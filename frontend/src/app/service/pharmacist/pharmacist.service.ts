import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {User} from "@app/model/users";
import {UpdateEmployee} from "@app/model/pharmderm/UpdateEmployee"
import { Reservation } from '@app/model/pharmderm/reservations';
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  constructor(private http:HttpClient) { }
  getPharmacistsByPharmacy(id : Number):Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getByPharmacy}/?id=${id}`);}

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

  registerPharmacist(pharmacist:Pharmacist):Observable<Pharmacist> {
    return this.http.post<Pharmacist>(`${environment.baseUrl}/${environment.pharmacist}/${environment.registerPharmacist}`, pharmacist);
  }
  deletePharmacist(pharmacistId:Number) {
    return this.http.post(`${environment.baseUrl}/${environment.pharmacist}/${environment.deletePharmacist}?pharmacistId=${pharmacistId}`,null);
  }
  getById(id:Number) : Observable<Pharmacist> {
    return this.http.get<Pharmacist>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getById}?pharmacistId=${id}`);
  }

  getAll():Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.getAll}`);
  }

  searchPharmacists(pharmacyId:Number,searchInput:String):Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.searchPharmacists}/?pharmacyId=${pharmacyId}&searchInput=${searchInput}`)
  }

  filterPharmacistsByGrade(pharmacists:Pharmacist[],grade:Number):Observable<Pharmacist[]>{
    return this.http.post<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.filterPharmacistsByGrade}/?grade=${grade}`,pharmacists);
  }

  filterPharmacistsByPharmacy(pharmacists:Pharmacist[],pharmacyId:Number):Observable<Pharmacist[]>{
    return this.http.post<Pharmacist[]>(`${environment.baseUrl}/${environment.pharmacist}/${environment.filterPharmacistsByPharmacy}/?pharmacyId=${pharmacyId}`,pharmacists);
  }

}
