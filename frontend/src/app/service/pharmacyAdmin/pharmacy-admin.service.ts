import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Patient} from "../../model/users/patient/patient";
import {environment} from "../../../environments/environment";
import {Pharmacy} from "../../model/pharmacy/pharmacy";
import {PharmacyAdmin} from "../../model/users/pharmacyAdmin/pharmacyAdmin";
import {User} from "@app/model/users";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PharmacyAdminService {
  public pharmacy:Pharmacy;
  public id:number;

  constructor(private http : HttpClient) { }

  getPharmacyByAdmin(id:number) : Observable<Pharmacy> {
    return this.http.get<Pharmacy>(`${environment.baseUrl}/pharmacyAdmin/getPharmacyByAdmin?id=${id}`);
  }
  //DANICE JEBEMU MATER????????????? PA KO OVAKO ZAKUCAVA JEBENOO AAAAAAAAAA
  getById(id:number) : Observable<PharmacyAdmin> {
    return this.http.get<PharmacyAdmin>(`${environment.baseUrl}/pharmacyAdmin/getById?id=${id}`);
  }
}
