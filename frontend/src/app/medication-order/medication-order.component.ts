import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "@app/service/user";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Medication} from "@app/model/medications/medication";
import {MedicationService} from "@app/service/medication/medication.service";
import {MedicationInOrder} from "@app/model/medicationOrder/medicationInOrder";
import {Supplier} from "@app/model/users/supplier/supplier";
import {PharmacyService} from "@app/service/pharmacy/pharmacy.service";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {OrderedMedication} from "@app/model/medicationOrder/orderedMedication";
import {OrdersService} from "@app/service/orders/orders.service";
import {Order} from "@app/model/medicationOrder/order";
import {MedicationOrdersService} from "@app/service/medication-orders/medication-orders.service";
import {MedicationOrderStatus} from "@app/model/medicationOrder/medicationOrderStatus";

@Component({
  selector: 'app-medication-order',
  templateUrl: './medication-order.component.html',
  styleUrls: ['./medication-order.component.css']
})
export class MedicationOrderComponent implements OnInit {

  addItem: boolean;
  addMedicationOrderForm: FormGroup;
  orderForm: FormGroup;
  medications:Medication[]=new Array();
  orderList:OrderedMedication[]=new Array();
  suppliers:Supplier[]=new Array();
  dueDate:Date;
  medication:Medication;
  amount:Number;
  currentUserId:Number;
  pharmacy:Pharmacy;
  medicationsInOrder:MedicationInOrder[]=new Array();
  today:Date = new Date();

  constructor(private router: Router
             ,private authenticationService: AuthenticationService
             ,private medicationService: MedicationService
             ,private pharmacyAdminService: PharmacyAdminService
              ,private medicationOrdersService: MedicationOrdersService) {

  }

  ngOnInit(): void {
    //document.getElementById('dueDateInput').setAttribute("min",this.today.toString());
    this.addItem = false;

    this.addMedicationOrderForm = new FormGroup({
      'medication' : new FormControl(null, Validators.required),
      'amount' : new FormControl(null,Validators.required)});

    this.orderForm = new FormGroup({
      'dueDate' : new FormControl(null,Validators.required)
  });

    this.medicationService.findAllMedications().subscribe(data=>
      {
        this.medications = data;
        console.log(this.medications);
      });
  }

  Logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  showAddForm() {
    this.addItem=true;

  }

  addOrder() {
      let orderItemExists=this.orderList.filter(order => order.medicationId==this.addMedicationOrderForm.value.medication.id).length==0;
      if(orderItemExists)
        {
          let medication=this.medications.filter(medication=>medication.id==this.addMedicationOrderForm.value.medication.id);
          let newOrder=new OrderedMedication(medication[0].id,medication[0].name,this.addMedicationOrderForm.value.amount);
          console.log(newOrder);
          this.orderList.push(newOrder);
        }
      else
        {
          for (var i in this.orderList)
          {
            if (this.orderList[i].medicationId == this.addMedicationOrderForm.value.medication.id)
            {
              this.orderList[i].quantity += this.addMedicationOrderForm.value.amount;
              console.log(this.orderList[i]);
              break;
            }
          }
        }

  }


  cancelItem(itemId:Number) {
    this.orderList = this.orderList.filter(listItem => listItem.medicationId !== itemId);
  }

  submitOrder() {
    this.currentUserId=Number(localStorage.getItem('userId'));
    console.log(this.currentUserId);
    this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
      result => {
        this.pharmacy = result;
        let order=new Order(null,this.orderList,this.orderForm.value.dueDate,this.pharmacy,null,MedicationOrderStatus.WAITING_FOR_OFFERS);
        this.medicationOrdersService.addOrder(order).subscribe(result =>{
          console.log(result);
          for(let i=0;i<this.orderList.length;i++){
            console.log(this.orderList[i]);
            let medicationInOrder=new MedicationInOrder(null,this.orderList[i],result);
            this.medicationsInOrder.push(medicationInOrder);
            console.log(this.medicationsInOrder);
          }
          this.medicationOrdersService.addMedicationInOrder(this.medicationsInOrder).subscribe(result =>{
            this.router.navigate(['/medication-order-offers']);
          });
        });

      });

    }
}
