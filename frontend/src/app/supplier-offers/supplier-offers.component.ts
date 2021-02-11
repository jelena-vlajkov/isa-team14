import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Address } from '@app/model/address/address';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { Offer } from '@app/model/users/supplier/offer';
import { Supplier } from '@app/model/users/supplier/supplier';
import { OffersService } from '@app/service/offers/offers.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-offers',
  templateUrl: './supplier-offers.component.html',
  styleUrls: ['./supplier-offers.component.css']
})
export class SupplierOffersComponent implements OnInit {
  public supplier : Supplier;
  public offers : Offer[];
  public offerForm : FormGroup;
  public edit : boolean = false;
  public selectedDate : Date;
  public today :Date;

  @ViewChild(GooglePlacesComponent) googleplaces;
  constructor(private offerService: OffersService, private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.today=new Date();
    this.loadSupplier();
    this.offerForm = new FormGroup({
      'price' : new FormControl(null, Validators.required),
      'dude' : new FormControl(null, Validators.required)
    });
  }

  supplierLogout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);
  }

  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
      });
      this.offerService.getAllOffersBySuppllier(Number(localStorage.getItem('userId'))).subscribe(data => 
        {
          this.offers = data;


        });

  }
  cancel(offer){
    offer.editing=false;
  }
  confirmEdit(offer){
    offer.dueDelivery = this.selectedDate;
    offer.price = this.offerForm.controls.price.value;
    this.offerService.updateOffer(offer).subscribe(
      res=>{
        alert('Success');
        this.loadSupplier();
      },
      error=>{
        alert("Fail - Editable date too soon!");
        offer.editing=false;
      }
      )
  }
  editOffer(offer){
    offer.editing=true;
    this.selectedDate=offer.dueDelivery;
    this.offerForm = new FormGroup({
      'price' : new FormControl(offer.price, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'dude' : new FormControl(offer.dueDelivery, Validators.required)
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