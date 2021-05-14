import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnswerToComplaint } from '@app/model/membershipinfo/answerToComplaint';
import { Complaint } from '@app/model/membershipinfo/complaint';
import { DermatologistComplaint } from '@app/model/membershipinfo/dermatologistComplaint';
import { PharmacistComplaint } from '@app/model/membershipinfo/pharmacistComplaint';
import { PharmacyComplaint } from '@app/model/membershipinfo/pharmacyComplaint';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintsService {

  constructor(private http: HttpClient) { }

  postComplaint(data : Complaint){
    return this.http.post(`${environment.baseUrl}/${environment.complaint}/${environment.add}`,data, {responseType : 'text'});
  }

  getUnansweredPharmacyComplaints() : Observable<PharmacyComplaint[]>{
    return this.http.get<PharmacyComplaint[]>(`${environment.baseUrl}/${environment.complaint}/${environment.getUnansweredPharmacy}`);
  }
  getUnansweredPharmacistComplaints() : Observable<PharmacistComplaint[]>{
    return this.http.get<PharmacistComplaint[]>(`${environment.baseUrl}/${environment.complaint}/${environment.getUnansweredPharmacist}`);
  }
  getUnansweredDermatoligstComplaints() : Observable<DermatologistComplaint[]>{
    return this.http.get<DermatologistComplaint[]>(`${environment.baseUrl}/${environment.complaint}/${environment.getUnansweredDermatologist}`);
  }

  answerToComplaint(data : AnswerToComplaint){
    return this.http.post(`${environment.baseUrl}/${environment.answerComplaint}/${environment.add}`,data, {responseType : 'text'});
  }
  
}
