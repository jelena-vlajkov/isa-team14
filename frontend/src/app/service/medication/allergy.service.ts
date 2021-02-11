import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Allergy } from '@app/model/medications/allergy';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AllergyService {

  constructor(private http : HttpClient) { 

  }    
  findAllAllergies(): Observable<Allergy[]> {
      return this.http.get<Allergy[]>(`${environment.baseUrl}/${environment.allergy}/${environment.findAll}`);
    }
}
