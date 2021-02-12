import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from '@app/model/users/patient/patient';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http : HttpClient) { }

  registerPatient(data : Patient){
    return this.http.post(`${environment.baseUrl}/${environment.patient}/${environment.add}`,data, {responseType : 'text'});
  }
  activatePatient(token : String){
    return this.http.post(`${environment.baseUrl}/${environment.patient}/${environment.activation}`,token, {responseType : 'text'});
  }
}
