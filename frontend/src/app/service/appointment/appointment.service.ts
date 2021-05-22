import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "@app/model/users";
import {environment} from "@environments/environment";
import {Examination} from "@app/model/appointment/examination";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http:HttpClient) { }

  getAvailableAppointmentsForDermatologists(dermatologistId:Number,pharmacyId:Number):Observable<Examination[]> {
    return this.http.get<Examination[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getAvailableExaminationsForDermatologist}/?medicalStaffId=${dermatologistId}&pharmacyId=${pharmacyId}`);
  }
}
