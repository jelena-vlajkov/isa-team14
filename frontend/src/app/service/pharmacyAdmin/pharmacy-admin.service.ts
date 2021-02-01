import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Patient} from "../../model/patient/patient";
import {environment} from "../../../environments/environment";
import {Pharmacy} from "../../model/pharmacy/pharmacy";

@Injectable({
  providedIn: 'root'
})
export class PharmacyAdminService {
  public pharmacy:Pharmacy;
  public id:number;

  constructor(private http : HttpClient,id) { }

 // getPharmacyByPharmacyAdmin(data:id){
  //  return this.http.get(`${environment.baseUrl}/getPharmacyByAdmin`,data,Pharmacy);
 // }
}
