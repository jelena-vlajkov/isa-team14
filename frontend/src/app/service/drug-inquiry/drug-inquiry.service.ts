import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "@environments/environment";
import {DrugInquiry} from "@app/model/reports/drugInquiry";

@Injectable({
  providedIn: 'root'
})
export class DrugInquiryService {

  constructor(private http : HttpClient) { }

  getAll():Observable<DrugInquiry[]> {
    return this.http.get<DrugInquiry[]>(`${environment.baseUrl}/${environment.drugInquiry}/${environment.getAll}`);
  }
}
