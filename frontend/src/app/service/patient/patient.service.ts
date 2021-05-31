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
import { PatientDrugReservation } from '@app/model/users/patient/patientDrugReservation';
import { Pharmacist } from '@app/model/users/pharmacist/pharmacist';
import {PatientScheduleCounseling} from '@app/model/users/patient/PatientScheduleCounseling'

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


    getDrugReservationForPatient(id : Number) : Observable<PatientDrugReservation[]> {
      return this.http.get<PatientDrugReservation[]>(`${environment.baseUrl}/${environment.reservations}/${environment.getDrugReservationForPatient}?patientId=${id}`);
    }

    
    cancelDrugReservation(reservationId : Number){
      return this.http.post(`${environment.baseUrl}/${environment.reservations}/${environment.cancelDrugReservation}`, reservationId, {responseType : 'text'});
    }

    findAvailablePharmacyByCounselingRange(startDate : String, endDate : String) : Observable<Pharmacy[]> {
      return this.http.get<Pharmacy[]>(`${environment.baseUrl}/${environment.appointment}/${environment.findAvailablePharmacyByCounselingRange}?start=${startDate}&end=${endDate}`);
    }

    findPharmacistsByRangeAndPharmacy(pharmacyId : Number, startDate : String, endDate : String) : Observable<Pharmacist[]> {
      return this.http.get<Pharmacist[]>
      (`${environment.baseUrl}/${environment.pharmacist}/${environment.findByRangeAndPharmacy}?pharmacyId=${pharmacyId}&start=${startDate}&end=${endDate}`);
    }

    findAndSchedulePatientCounseling(scheduleCounseling : PatientScheduleCounseling) {
      return this.http.post(`${environment.baseUrl}/${environment.appointment}/${environment.findAndSchedulePatientCounseling}`, scheduleCounseling, {responseType : 'text'});
    }
   

}
