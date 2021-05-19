import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-patient-home-page',
  templateUrl: './patient-home-page.component.html',
  styleUrls: ['./patient-home-page.component.css']
})
export class PatientHomePageComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
  }

  patientLogOut(){
    this.authenticationService.logout();
  }

}
