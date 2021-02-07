import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { OrderedMedication } from '@app/model/medications/orderedMedication';
import { Offer } from '@app/model/users/supplier/offer';
import { OfferStatus } from '@app/model/users/supplier/offerStatus';
import { Order } from '@app/model/users/supplier/order';
import { Supplier } from '@app/model/users/supplier/supplier';
import { OffersService } from '@app/service/offers/offers.service';
import { OrdersService } from '@app/service/orders/orders.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-orders',
  templateUrl: './supplier-orders.component.html',
  styleUrls: ['./supplier-orders.component.css']
})
export class SupplierOrdersComponent implements OnInit {
  public supplier : Supplier;
  public offerGroup : FormGroup;
  public orders : Order[];
  public showmore : boolean = false;
  public orderedMedication : OrderedMedication[];
  public myfirstorder : Order;
  public displayedColumns: string[] = ['name', 'quantity'];
  public selectedOrder : Order;
  public today :Date;
  public selectedDate : Date;
  public unique : number;
  public myOffer : Offer;
  public orderimmakillmyself : Order;
  public dataSource = new MatTableDataSource<OrderedMedication>();
  public helpmepls : Order;
  constructor(private offerService: OffersService, private orderService: OrdersService, private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.loadSupplier();
    this.today=new Date();

  }
  showMoreInfo(order){
    this.unique = order.uniqueidentifier;
    this.showmore = true;
    this.selectedOrder = new Order(order.dueDate, null, order.pharmacy, order.editableDue, order.uniqueidentifier);
    this.orderService.getOrderedMedicationByIdentifier(order.uniqueidentifier).subscribe(
      data=>{
        this.orderedMedication = data;
        this.dataSource = new MatTableDataSource(data);
        this.selectedOrder.orderedMedication = data;
      }
    );

    this.offerGroup = new FormGroup({
      'price' : new FormControl(null, Validators.required),
      'delivery' : new FormControl(null, Validators.required)
    })

  }
  hashCode(str) {
    var hash = 0, i, chr;
    for (i = 0; i < str.length; i++) {
      chr   = str.charCodeAt(i);
      hash  = ((hash << 5) - hash) + chr;
      hash |= 0; // Convert to 32bit integer
    }
    var currdate = new Date();
    var currmillis = currdate.getMilliseconds();
    return hash + currmillis;;
  }

  giveOffer(){
    /*


    public id : Number;
    public supplier: Supplier;
    public order :Order;
    public offerStatus : OfferStatus;
    public uniqueidentifier : number;
    public price : Number;
    public dueDelivery : Date;
    public editing : boolean;
    */
    // this.orderService.getByUniqueId(this.unique).subscribe(
    //   data=>{
    //     this.orderimmakillmyself = data;
    //   }

    // );
    // this.helpmepls = new Order(null, null, null, null, this.unique);
    this.supplier.id = Number(localStorage.getItem('userId'));
    this.myOffer = new Offer(null, this.supplier, this.selectedOrder, OfferStatus.PENDING, this.hashCode(this.supplier.email), this.offerGroup.controls.price.value, this.offerGroup.controls.delivery.value);
    this.offerService.giveOffer(this.myOffer).subscribe(
      res=>{
        alert('Success');
        this.loadSupplier();
        this.showmore = false;

      },
      error=>{
        alert("Fail - Editable date too soon or insuficient funds!");
      }
    )
  }
  supplierLogout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);

  }
  back(){
    this.showmore = false;
  }

  
  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
      });
      this.orderService.getAllUnfinishedOrders().subscribe(data => 
        {
          this.orders = data;
        });      

  }


  routeToProfile(){
    this.router.navigate(['/supplier']);

  }
  routeToOffers(){
      this.router.navigate(['/supplier/offers']);
  }

  routeToOrders(){
      this.router.navigate(['/supplier/allOrders']);
  }


}


