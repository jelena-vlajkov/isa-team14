import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from 'src/app/model/patient/patient';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';        

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http : HttpClient) { }

    getLoggedPatient() : Observable<Patient> {    
        return this.http.get<Patient>(`${environment.baseUrl}/getLoggedUser`)
    }

    getPatientById(id:number) : Observable<Patient> {    
      return this.http.get<Patient>(`${environment.baseUrl}/patient/getById/?id=${id}`);
    }

    updatePatient(data : Patient){
      return this.http.post(`${environment.baseUrl}/patient/editPatient`,data, {responseType : 'text'});
    }
}
