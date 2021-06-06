import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/service/user';
import { PatientService } from '@app/service/patient/patient.service' 

@Component({
  selector: 'app-patient-home-page',
  templateUrl: './patient-home-page.component.html',
  styleUrls: ['./patient-home-page.component.css']
})
export class PatientHomePageComponent implements OnInit {

  constructor(private authenticationService : AuthenticationService, private patientService : PatientService) { }

  ngOnInit(): void {
    this.patientService.getNumberOfPatientPenalties(this.authenticationService.currentUserValue.id).subscribe(
      res => {
        localStorage.setItem("patientPenalty", res.toString())
        console.log(res)
      }
    )

  }

  patientLogOut(){
    this.authenticationService.logout();
  }

}
