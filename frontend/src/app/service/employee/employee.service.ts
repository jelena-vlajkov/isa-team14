import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import {UpdateEmployee} from "@app/model/pharmderm/UpdateEmployee"
import { EmployeePasswordChanger } from '@app/model/pharmderm/changepass';
import { FirstTimePasswordChange } from '@app/model/users/firstTimePasswordChange';
import { PatientsOverview } from '@app/model/pharmderm/patientoverview';
import {SearchParam} from '@app/model/pharmderm/searchparams'
import { Patient } from '@app/model/users/patient/patient';
import { env } from 'process';
import { Appointment } from '@app/model/appointment/appointment';
import { Medication } from '@app/model/medications/medication';
import { PrescribeMedication } from '@app/model/pharmderm/prescribemeds';
import { MedicationsToRecommend } from '@app/model/pharmderm/medicationstorecommend';
import { CreatePenalty } from '@app/model/pharmderm/createpenalty';
import { SaveReport } from '@app/model/pharmderm/createreport';
import { CreaeteReservation } from '@app/model/pharmderm/createreservation';

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

  getAllPatientsByMedicalStaff(medicalStaffId : Number) : Observable<PatientsOverview[]> {
    return this.http.get<PatientsOverview[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getPatientsByMedicalStaff}?medicalStaffId=${medicalStaffId}`);
  }

  searchPatientsByParams(searchParams : SearchParam) : Observable<PatientsOverview[]> {
    return this.http.post<PatientsOverview[]>(`${environment.baseUrl}/${environment.appointment}/${environment.searchPatients}`, searchParams);
  }

  getScheduledAppointmentsForDate(medicalStaffId : Number, date : string) : Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${environment.baseUrl}/${environment.appointment}/${environment.scheduledAppointments}?date=${date}&id=${medicalStaffId}`);
  }

  getAvailable(medicalStaffId : Number, date : string, pharmacyid : Number) : Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${environment.baseUrl}/${environment.appointment}/${environment.findAvailableForEmployee}?date=${date}&medicalStaffId=${medicalStaffId}&pharmacyId=${pharmacyid}`);
  }

  recommendMedications(patientId : Number) : Observable<MedicationsToRecommend[]> {
    return this.http.get<MedicationsToRecommend[]>(`${environment.baseUrl}/${environment.medicalRecord}/${environment.recommendMedications}?patientId=${patientId}`);
  }

  recommendAvailableMedications(patientId : Number, pharmacyId : Number) : Observable<MedicationsToRecommend[]> {
    return this.http.get<MedicationsToRecommend[]>(`${environment.baseUrl}/${environment.medicalRecord}/${environment.recommendMedications}?patientId=${patientId}&pharmacyId=${pharmacyId}`);
  }

  recommendSimilarMedications(medicationId : Number, pharmacyId : Number) : Observable<MedicationsToRecommend[]> {
    return this.http.get<MedicationsToRecommend[]>(`${environment.baseUrl}/${environment.medicalRecord}/${environment.recommendSimilarMedication}?medicationId=${medicationId}&pharmacyId=${pharmacyId}`);
  }

  addPenalty(penalty : CreatePenalty) : Observable<Response> {
    return this.http.post<Response>(`${environment.baseUrl}/${environment.penalty}/${environment.savePenalty}`, penalty);
  }

  addReport(report : SaveReport) : Observable<Response> {
    return this.http.post<Response>(`${environment.baseUrl}/${environment.reports}/${environment.saveReport}`, report);
  }

  addDrugReservation(drugReservation : CreaeteReservation) : Observable<Response> {
    return this.http.post<Response>(`${environment.baseUrl}/${environment.reservations}/${environment.saveResevation}`, drugReservation);
  }

  scheduleAppointment(appointment : Appointment) : Observable<Response> {
    return this.http.post<Response>(`${environment.baseUrl}/${environment.appointment}/${environment.scheduleAppointment}`, appointment);
  }

  finishAppointment(appointmentId : Number) : Observable<Response> {
    return this.http.post<Response>(`${environment.baseUrl}/${environment.appointment}/${environment.finishAppointment}`, appointmentId);
  }






}