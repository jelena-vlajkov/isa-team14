import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "@environments/environment";
import {HttpClient} from "@angular/common/http";
import {User} from "@app/model/users/user";

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  constructor(private http:HttpClient) { }
  getPharmacistsByPharmacy(id : Number):Observable<User[]>{
    return this.http.get<User[]>(`${environment.baseUrl}/pharmacists/getByPharmacy/?id=${id}`);
  }
}
