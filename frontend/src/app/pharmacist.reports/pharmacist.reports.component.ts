import { Component } from '@angular/core';

import { AuthenticationService } from '../service/user/authentication.service'
@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharmacist.reports.component.html',
  styleUrls: ['./pharmacist.reports.component.css']
})
export class PharmacistReportsComponent {
  constructor(private authService : AuthenticationService) {} 
  

  logout() {
    this.authService.logout();
  }
 
}