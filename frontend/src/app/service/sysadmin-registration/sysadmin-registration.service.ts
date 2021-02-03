import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SystemAdmin } from '@app/model/users/systemAdmin/systemAdmin';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SysadminRegistrationService {

  constructor(private http : HttpClient) { }
  registerSysAdmin(data : SystemAdmin){
    return this.http.post(`${environment.baseUrl}/${environment.admin}/${environment.add}`,data, {responseType : 'text'});
  }
}
