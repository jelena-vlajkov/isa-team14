import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "@app/service/user";
import {Router} from "@angular/router";
import {OffersService} from "@app/service/offers/offers.service";
import {OrdersService} from "@app/service/orders/orders.service";
import {Offer} from "@app/model/users/supplier/offer";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {MedicationOrdersService} from "@app/service/medication-orders/medication-orders.service";
import {Order} from "@app/model/medicationOrder/order";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-medication-order-offers',
  templateUrl: './medication-order-offers.component.html',
  styleUrls: ['./medication-order-offers.component.css']
})
export class MedicationOrderOffersComponent implements OnInit {
  offersForOrders:Offer[]=new Array();
  pharmacyId:Number;
  private StringIsNumber = value => isNaN(Number(value)) === false;
  medicationOrders:Order[]=new Array();
  showMedicationOrderInfo : boolean = false;
  showOffers : boolean = true;
  medicationOrder : Order;
  allMedicationOrders : boolean = false;
  filter:String;

  constructor(private authenticationService:AuthenticationService
              ,private router:Router
              ,private offerService:OffersService
              ,private medicationOrderService:MedicationOrdersService
              ,private pharmacyAdminService:PharmacyAdminService) { }

  ngOnInit(): void {
   this.pharmacyAdminService.getPharmacyByAdmin(Number(localStorage.getItem('userId'))).subscribe(
      result => {
        this.pharmacyId = result.id;
        console.log(this.pharmacyId);
        this.medicationOrderService.getOrdersByPharmacy(this.pharmacyId).subscribe(result => {
          result = this.toArray(result);
          this.medicationOrders = result;
          for(let i=0; i<result.length;i++){
            this.offerService.getAllByOrder(result[i].id).subscribe(result => {
                this.offersForOrders.push.apply(this.offersForOrders,this.toArray(result));

            });
          }
          console.log(this.offersForOrders);
        });
      });

  }

  checkLoggedInUser(){
    return this.authenticationService.getUserValue();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  toArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }

  seeMedicationOrderInfo(id: Number) {
    this.showMedicationOrderInfo = true;
    this.showOffers = false;
    this.allMedicationOrders = false;
    this.medicationOrderService.findById(id).subscribe(result =>{
        this.medicationOrder = result;
    });
  }

  cancelMedicationOrderInfoDialog() {
    this.showMedicationOrderInfo = false;
    this.showOffers = true;
    this.allMedicationOrders = false;
  }

  chooseOffer(offer: Offer) {
    this.offerService.chooseOffer(offer).subscribe(result => {
      this.offersForOrders=new Array();
      this.ngOnInit();
    });
  }
  offersExists():boolean{
    return this.offersForOrders.length>0;
  }

  viewAllMedicationOrders() {
    this.showMedicationOrderInfo =false;
    this.showOffers = false;
    this.allMedicationOrders = true;
  }

  cancelAllMedicationOrderInfoDialog() {
    this.showMedicationOrderInfo = false;
    this.showOffers = true;
    this.allMedicationOrders = false;
  }


  filterMedicationOrders() {
    this.medicationOrderService.getOrdersByPharmacy(this.pharmacyId).subscribe(result => {
      result = this.toArray(result);
      this.medicationOrders = result
      if (this.filter != "ALL") {
        this.medicationOrders = this.medicationOrders.filter(medicationOrder => medicationOrder.status.toString() == this.filter);
      }
    });
  }
}
