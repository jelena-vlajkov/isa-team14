import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SysadminRegistrationService {

  constructor(private http : HttpClient) { }
  registerSysAdmin(data : SystemAdmin){
    return this.http.post(`${environment.baseUrl}/${environment.admin}/${environment.add}`,data, {responseType : 'text'});
  }
  getSysAdmin(id:Number) : Observable<SystemAdmin> {
    return this.http.get<SystemAdmin>(`${environment.baseUrl}/${environment.admin}/${environment.getById}?id=${id}`);
  }
  updateSysAdmin(data : SystemAdmin){
    return this.http.post(`${environment.baseUrl}/${environment.admin}/${environment.update}`,data, {responseType : 'text'});
  }
}
