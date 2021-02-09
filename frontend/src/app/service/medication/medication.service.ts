import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medication } from 'src/app/model/medications/medication';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {

  constructor(private http : HttpClient) { }

  findAllMedications(): Observable<Medication[]> {
    return this.http.get<Medication[]>(`${environment.baseUrl}/${environment.medication}/${environment.findAll}`);
  }

  addMedication(data : Medication) {
    return this.http.post(`${environment.baseUrl}/${environment.medication}/${environment.add}`,data, {responseType : 'text'});
  }
  findByName(name : String): Observable<Medication[]>{
    return this.http.get<Medication[]>(`${environment.baseUrl}/${environment.medication}/${environment.getByName}?name=${name}`);
  }
}
