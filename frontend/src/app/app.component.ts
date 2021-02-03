import { Component } from '@angular/core';
import {AuthenticationService} from "@app/service/user";
import {AuthenticatedUser} from "@app/models/authenticatedUser";
import {Router} from "@angular/router";
import {Role} from "@app/models";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: AuthenticatedUser;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  onInit(){
    this.router.navigate(['']);
  }
  get isAdmin() {
    return this.currentUser && this.currentUser.role === Role.Dermatologist;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
