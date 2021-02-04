import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  constructor(private http : HttpClient) { }



}
