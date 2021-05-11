import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from "../service/user";
import {IngredientService} from "../service/medication/ingredients.service"
import {ReportsService} from "../service/reports/reports.service"
import { GooglePlacesComponent } from '@app/google-places/google-places.component';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: '/pharmacist/profile', title: 'Profile',  icon: 'person', class: '' },
    { path: '/pharmacist/calendar', title: 'Calendar',  icon: 'calendar', class: ''}
];

@Component({
    selector: 'app-pharmacist',
    templateUrl: './pharmacist.component.html',
    styleUrls: ['./pharmacist.component.css']
  })

export class PharmacistComponent implements OnInit {
  
  menuItems: any[];
  editProfileForm: FormGroup;

  constructor(private authService: AuthenticationService, private router: Router, private ingredientService : IngredientService, private reportsService : ReportsService) { }

  ngOnInit(): void {
    console.log(this.authService.currentUserValue.token);
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.ingredientService.findAllIngredients().subscribe();
    this.reportsService.getReports().subscribe();
  
  }

  logout() {
    this.authService.logout();
  }

  routeToPatients() {
    this.router.navigateByUrl('/patients-overview');
  }
  routeToMedications() {
    this.router.navigateByUrl('/employee-medications');
  }
  routeToCounselings() {
    this.router.navigateByUrl('/add-report');
  }
  routeToReports() {
    this.router.navigateByUrl('/appointment-report');
  }
  routeToProfile() {
    this.router.navigateByUrl('/employee-profile');
  }
  routeToCalendar() {
    this.router.navigateByUrl('/employee-profile');

  }
}

