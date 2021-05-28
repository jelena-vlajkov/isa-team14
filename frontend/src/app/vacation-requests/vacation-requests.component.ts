import { Component, OnInit } from '@angular/core';
import {VacationRequest} from "@app/model/pharmderm/vacationrequest";
import {VacationRequestsService} from "@app/service/vacation-requests/vacation-requests.service";
import {PharmacyAdmin} from "@app/model/users/pharmacyAdmin/pharmacyAdmin";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {VacationRequestAnswer} from "@app/model/users/vacationRequestAnswer";

@Component({
  selector: 'app-vacation-requests',
  templateUrl: './vacation-requests.component.html',
  styleUrls: ['./vacation-requests.component.css']
})
export class VacationRequestsComponent implements OnInit {
  vacationRequests:VacationRequest[]=new Array();
  pharmacy:Pharmacy;
  requestRejected:boolean=false;
  explanation:String;
  rejectedRequestId:Number;

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
    let vacationRequest=this.vacationRequests.filter(vacationRequest => vacationRequest.id == id)[0];
    let answer=new VacationRequestAnswer(vacationRequest,true,null);
    console.log(answer);
    this.vacationRequestsService.approveVacationRequest(answer).subscribe(result => {
      this.getVacationRequestsByPharmacy();
    });
  }

  denyVacationRequest() {
    console.log(this.explanation);
    let vacationRequest=this.vacationRequests.filter(vacationRequest => vacationRequest.id == this.rejectedRequestId)[0];
    let answer=new VacationRequestAnswer(vacationRequest,false,this.explanation);
    console.log(answer);
    this.vacationRequestsService.denyVacationRequest(answer).subscribe(result => {
      this.getVacationRequestsByPharmacy();
      this.requestRejected = false;
    });
  }

  reject(id: Number) {
    this.requestRejected =true;
    this.rejectedRequestId = id;
  }

  rejectingCanceled() {
    this.requestRejected = false;
  }
}
