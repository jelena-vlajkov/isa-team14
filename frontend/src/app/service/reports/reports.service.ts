import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from 'src/app/model/users/patient/patient';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  constructor(private http : HttpClient) { }

  getReports(){
    return this.http.get(`${environment.baseUrl}/${environment.reservations}/${environment.getReservations}`);
  }

}
