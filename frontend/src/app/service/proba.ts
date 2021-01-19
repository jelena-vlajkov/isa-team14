import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { env } from 'process';

@Injectable({
  providedIn: 'root'
})
export class Proba {

  constructor(private http : HttpClient) { }


  proba() {
    return this.http.get(`${environment.baseUrl}/${environment.proba}`);
  }
}
  
