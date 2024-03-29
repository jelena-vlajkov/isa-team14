import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Supplier} from "@app/model/users/supplier/supplier";
import {environment} from "@environments/environment";
import {WorkDay} from "@app/model/schedule/workDay";

@Injectable({
  providedIn: 'root'
})
export class WorkdayService {

  constructor(private http: HttpClient) {}

  addWorkday(data : WorkDay){
    return this.http.post(`${environment.baseUrl}/${environment.workDay}/${environment.addWorkDay}`,data );
  }
  getWorkdaysByMedicalStaff(medicalStaffId:Number){
    return this.http.get(`${environment.baseUrl}/${environment.workDay}/${environment.getByMedicalStaff}/?medicalStaffId=${medicalStaffId}`);
  }
}
