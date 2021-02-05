import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Address } from '@app/model/address/address';
import { PasswordChanger } from '@app/model/users/passwordChanger';
import { Supplier } from '@app/model/users/supplier/supplier';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-offers',
  templateUrl: './supplier-offers.component.html',
  styleUrls: ['./supplier-offers.component.css']
})
export class SupplierOffersComponent implements OnInit {
  public supplier : Supplier;
  @ViewChild(GooglePlacesComponent) googleplaces;
  constructor(private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.loadSupplier();

  }

  supplierLogout(){
      this.authenticationService.logout();
    
  }
  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
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