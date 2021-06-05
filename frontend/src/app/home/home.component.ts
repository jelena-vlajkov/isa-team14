import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/model/users';
import { UserService, AuthenticationService } from '@app/service/user';
import {AuthenticatedUser} from "@app/model/users/authenticatedUser";
import { Router } from '@angular/router';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
  loading = false;
  currentUser: AuthenticatedUser;
  userFromApi: User;

  constructor(
    private userService: UserService,
    private authenticationService: AuthenticationService,
    private router : Router
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
    if (this.authenticationService.currentUserValue != undefined) {
      if (this.authenticationService.currentUserValue.role === 'Pharmacist' || this.authenticationService.currentUserValue.role === 'Dermatologist') {
        this.router.navigate(["/dashboard"])
      }
    }
    this.loading = true;
    this.userService.getById(this.currentUser.id).pipe(first()).subscribe(user => {
      this.loading = false;
      this.userFromApi = user;
    });
  }}
