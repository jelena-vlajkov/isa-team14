import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../service/user/authentication.service'
@Component({
  selector: 'pharmacist-reports',
  templateUrl: './pharmacist.reports.component.html',
  styleUrls: ['./pharmacist.reports.component.css']
})
export class PharmacistReportsComponent {
  constructor(private authService : AuthenticationService, private router : Router) {} 
  
  ngOnInit(): void {
      
    if ((localStorage.getItem('firstTimeChanged') === 'false')) { 
      this.router.navigate(["/employee-welcome"]);

    }
  }

  logout() {
    this.authService.logout();
  }
 
}