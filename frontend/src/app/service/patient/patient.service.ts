import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from 'src/app/model/users/patient/patient';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';        
import { Appointment } from '@app/model/appointment/appointment';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http : HttpClient) { }

    getLoggedPatient() : Observable<Patient> {    
        return this.http.get<Patient>(`${environment.baseUrl}/getLoggedUser`)
    }

    getPatientById(id:number) : Observable<Patient> {    
     // return this.http.get<Patient>(`${environment.baseUrl}/patient/getById?id=${id}`);
     return this.http.get<Patient>(`${environment.baseUrl}/${environment.patient}/getLoggedPatient`);
    }

    updatePatient(data : Patient){
      return this.http.post(`${environment.baseUrl}/${environment.patient}/${environment.editPatient}`,data, {responseType : 'text'});
    }

    getFinishedPatientsCounselings(id : Number): Observable<Appointment[]> {
      return this.http.get<Appointment[]>
      (`${environment.baseUrl}/${environment.appointment}/${environment.getFinishedPatientsCounselings}?patientId=${id}`);
    }

    getFinishedPatientsExaminations(id : Number): Observable<Appointment[]> {
      return this.http.get<Appointment[]>
      (`${environment.baseUrl}/${environment.appointment}/${environment.getFinishedPatientsExaminations}?patientId=${id}`);
    }
}
