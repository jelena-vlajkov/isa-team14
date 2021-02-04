import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import {Authentication} from "@app/model/users/authentication";
import {AuthenticatedUser} from "@app/model/users/authenticatedUser";
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<AuthenticatedUser>;
    public currentUser: Observable<AuthenticatedUser>;

    constructor(private http: HttpClient, private router : Router) {
        this.currentUserSubject = new BehaviorSubject<AuthenticatedUser>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): AuthenticatedUser {
        return this.currentUserSubject.value;
    }

  login(credentials: Authentication){

    
      return this.http.post<AuthenticatedUser>(`${environment.baseUrl}/${environment.auth}/${environment.login}`, credentials)
      .pipe(map(response => {
        localStorage.setItem('currentUser', JSON.stringify(response));
        console.log(response.token);
        this.currentUserSubject.next(response);
        return response;
      }));
  }

  logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
        this.router.navigate(['/']);
  }
}
