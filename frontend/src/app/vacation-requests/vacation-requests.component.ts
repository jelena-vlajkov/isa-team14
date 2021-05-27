import { Component, OnInit } from '@angular/core';
import {VacationRequest} from "@app/model/pharmderm/vacationrequest";
import {VacationRequestsService} from "@app/service/vacation-requests/vacation-requests.service";
import {PharmacyAdmin} from "@app/model/users/pharmacyAdmin/pharmacyAdmin";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

@Component({
  selector: 'app-vacation-requests',
  templateUrl: './vacation-requests.component.html',
  styleUrls: ['./vacation-requests.component.css']
})
export class VacationRequestsComponent implements OnInit {
  vacationRequests:VacationRequest[]=new Array();
  pharmacy:Pharmacy;

  constructor(private vacationRequestsService:VacationRequestsService,
              private pharmacyAdminService:PharmacyAdminService) { }

  ngOnInit(): void {
    this.pharmacyAdminService.getPharmacyByAdmin(Number(localStorage.getItem("userId"))).subscribe(result =>{
      this.pharmacy = result;
      console.log(this.pharmacy);
      this.getVacationRequestsByPharmacy();
    });

  }

  getVacationRequestsByPharmacy(){
    this.vacationRequestsService.getAllByPharmacy(this.pharmacy.id).subscribe(result=>{
      this.vacationRequests = result;
      console.log(this.vacationRequests);
    });
  }
  vacationExists(){
    return this.vacationRequests.length != 0;
  }

  approveVacationRequest(id: Number) {
    this.vacationRequestsService.approveVacationRequest(id).subscribe(result => {
      this.getVacationRequestsByPharmacy();
    });
  }

  denyVacationRequest(id: Number) {
    this.vacationRequestsService.denyVacationRequest(id).subscribe(result => {
      this.getVacationRequestsByPharmacy();
    });
  }
}
