import { Component } from '@angular/core';
import { from } from 'rxjs';
import {AuthenticationService} from '../service/user/authentication.service'

@Component({
  selector: 'pharmacist-calendar',
  templateUrl: './pharmacist.calendar.component.html',
  styleUrls: ['./pharmacist.calendar.component.css']
})
export class PharmacistCalendarComponent {
  constructor(private authService: AuthenticationService) { }

  logout() {
    this.authService.logout();
  }

}