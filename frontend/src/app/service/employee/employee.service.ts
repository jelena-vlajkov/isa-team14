import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {UpdateEmployee} from "@app/model/pharmderm/UpdateEmployee"
import { EmployeePasswordChanger } from '@app/model/pharmderm/changepass';
import { FirstTimePasswordChange } from '@app/model/users/firstTimePasswordChange';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }
  updateEmployee(employee : UpdateEmployee) : Observable<UpdateEmployee[]> {
    return this.http.post<UpdateEmployee[]>(`${environment.baseUrl}/${environment.updateEmployees}`, employee);
  }
  changeEmployeePassword(passChange : EmployeePasswordChanger) : Observable<EmployeePasswordChanger[]> {
      return this.http.post<EmployeePasswordChanger[]>(`${environment.baseUrl}/${environment.changeEmployeePass}`, passChange);
  }

  firstTimePasswordChange(passChange : FirstTimePasswordChange) : Observable<FirstTimePasswordChange[]> {
    return this.http.post<FirstTimePasswordChange[]>(`${environment.baseUrl}/${environment.firstTimePasswordChange}`, passChange);
}
}