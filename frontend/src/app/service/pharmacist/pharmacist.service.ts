import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "@app/models";
import {environment} from "@environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  constructor(private http:HttpClient) { }
  getPharmacistsByPharmacy(id:Number):Observable<User[]>{
    return this.http.get<User[]>(`${environment.baseUrl}/pharmacists/getByPharmacy/?id=${id}`);
  }
}
