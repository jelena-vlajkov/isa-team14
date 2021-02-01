import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import {Authentication} from "@app/models/authentication";
import {AuthenticatedUser} from "@app/models/authenticatedUser";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<AuthenticatedUser>;
    public currentUser: Observable<AuthenticatedUser>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<AuthenticatedUser>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): AuthenticatedUser {
        return this.currentUserSubject.value;
    }

  login(credentials: Authentication){
    return this.http.post<AuthenticatedUser>(`${environment.baseUrl}/auth/${environment.login}`, credentials)
      .pipe(map(response => {
        localStorage.setItem('currentUser', JSON.stringify(response));
        this.currentUserSubject.next(response);
        return response;
      }));
  }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
