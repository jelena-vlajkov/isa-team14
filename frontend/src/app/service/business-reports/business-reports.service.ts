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

  getNumberOfScheduledByMonth(year:Number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForMonth}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfScheduledForHalfYear(year:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForHalfYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfScheduledForYear(startYear:number,endYear:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getNumberOfScheduledForYear}/?startYear=${startYear}&endYear=${endYear}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsByMonth(year:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForMonth}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsForHalfYear(year:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForHalfYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getNumberOfIssuedDrugsForYear(startYear:number,endYear:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getDrugConsumptionsForYear}/?startYear=${startYear}&endYear=${endYear}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForMonth(year:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForMonth}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForHalfYear(year:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForHalfYear}/?year=${year}&pharmacyId=${pharmacyId}`);
  }

  getPharmacyIncomeForYear(startYear:number,endYear:number,pharmacyId:Number):Observable<number[]> {
    return this.http.get<number[]>(`${environment.baseUrl}/${environment.pharmacyBussinesReport}/${environment.getPharmacyIncomeForYear}/?startYear=${startYear}&endYear=${endYear}&pharmacyId=${pharmacyId}`);
  }
}
