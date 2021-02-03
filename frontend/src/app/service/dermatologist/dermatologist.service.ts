import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "@app/models";
import {environment} from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  constructor(private http : HttpClient) { }

  getDermatologistByPharmacy(id:number):Observable<User[]>{
    return this.http.get<User[]>(`${environment.baseUrl}/dermatologist/getByPharmacy/?id=${id}`);
  }
}
