import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "@environments/environment";
import {VacationRequest} from "@app/model/pharmderm/vacationrequest";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VacationRequestsService {

  constructor(private http:HttpClient) { }

  getAllByPharmacy(pharmacyId:Number):Observable<VacationRequest[]>{
    return this.http.get<VacationRequest[]>(`${environment.baseUrl}/${environment.vacationRequest}/${environment.getByPharmacy}?pharmacyId=${pharmacyId}`);
  }

  approveVacationRequest(vacationRequestId:Number){
    return this.http.post(`${environment.baseUrl}/${environment.vacationRequest}/${environment.approveVacationRequest}?vacationRequestId=${vacationRequestId}`,null);
  }

  denyVacationRequest(vacationRequestId:Number){
    return this.http.post(`${environment.baseUrl}/${environment.vacationRequest}/${environment.denyVacationRequest}?vacationRequestId=${vacationRequestId}`,null);
  }
}
