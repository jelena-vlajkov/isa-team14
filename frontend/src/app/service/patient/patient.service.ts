import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from 'src/app/model/users/patient/patient';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';        
import { Appointment } from '@app/model/appointment/appointment';
import { EPrescription } from '@app/model/medications/ePrescription';
import { PrescribedEdrugs } from '@app/model/medications/prescibedEDrugs';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { env } from 'process';
import { CreaeteReservation } from '@app/model/pharmderm/createreservation'

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

    getNotFinishedAppointmentsForPatient(id : Number): Observable<Appointment[]> {
      return this.http.get<Appointment[]>
      (`${environment.baseUrl}/${environment.appointment}/${environment.getNotFinishedAppointmentsForPatient}?patientId=${id}`);
    }

    cancelAppointment(id : Number){
      return this.http.post(`${environment.baseUrl}/${environment.appointment}/${environment.cancelAppointment}`, id, {responseType : 'text'});
    }

    getPatientEPrescriptions(id : Number): Observable<EPrescription[]> {
      return this.http.get<EPrescription[]>
      (`${environment.baseUrl}/${environment.ePrescription}/${environment.getPatientEPrescriptions}?patientId=${id}`);
    }

    
    getAllPrescribedDrugForPatient(id : Number): Observable<PrescribedEdrugs[]> {
      return this.http.get<PrescribedEdrugs[]>
      (`${environment.baseUrl}/${environment.ePrescription}/${environment.getAllPrescribedDrugForPatient}?patientId=${id}`);
    }

    getPharmaciesByMedicationId(id : Number) : Observable<Pharmacy[]> {
      return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.pharmacy}/${environment.getPharmaciesByMedicationId}?id=${id}`);
    }

    createDrugReservation(reservation : CreaeteReservation) {
      return this.http.post(`${environment.baseUrl}/${environment.reservations}/${environment.patientDrugReservation}`, reservation, {responseType : 'text'});
    }



}
