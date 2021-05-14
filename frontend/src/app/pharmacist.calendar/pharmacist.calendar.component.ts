import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { from } from 'rxjs';
import {AuthenticationService} from '../service/user/authentication.service'

@Component({
  selector: 'pharmacist-calendar',
  templateUrl: './pharmacist.calendar.component.html',
  styleUrls: ['./pharmacist.calendar.component.css']
})
export class PharmacistCalendarComponent {

  constructor(private authService: AuthenticationService, private router : Router) { }
  ngOnInit() {
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);

    }
  }
  logout() {
    this.authService.logout();
  }

}