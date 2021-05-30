import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Examination} from "@app/model/appointment/examination";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BusinessReportsService {

  constructor(private http:HttpClient) { }

  getNumberOfScheduledByMonth(month:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForMonth}/?month=${month}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfScheduledForHalfYear(part:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForHalfYear}/?part=${part}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfScheduledForYear(year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsByMonth(month:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForMonth}/?month=${month}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsForHalfYear(part:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForHalfYear}/?part=${part}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsForYear(year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForMonth(month:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForMonth}/?month=${month}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForHalfYear(part:number,year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForHalfYear}/?part=${part}&year=${year}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForYear(year:number,pharmacyId:Number):Observable<number> {
    return this.http.get<number>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }
}
