import { Router } from '@angular/router';
import { AuthenticatedUser } from './../../model/users/authenticatedUser';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credentials } from '../../model/authentication/credentials';
import { environment } from 'src/environments/environment';
import { map, tap } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private userSubject : BehaviorSubject<AuthenticatedUser>;
  public user : Observable<AuthenticatedUser>;

  constructor(private http: HttpClient, private router : Router) {

    this.userSubject = new BehaviorSubject<AuthenticatedUser>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public getUserValue() : AuthenticatedUser {
    return this.userSubject.value;
  }


  login(credentials: Credentials){
    return this.http.post<AuthenticatedUser>(`${environment.baseUrl}/auth/${environment.login}`, credentials)
    .pipe(map(response => {
        localStorage.setItem('user', JSON.stringify(response));
        this.userSubject.next(response);
        return response;
    }));
  }

  logout(){
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }
}
