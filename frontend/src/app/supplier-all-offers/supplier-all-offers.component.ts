import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { GooglePlacesComponent } from '@app/google-places/google-places.component';
import { Offer } from '@app/model/users/supplier/offer';
import { OfferStatus } from '@app/model/users/supplier/offerStatus';
import { Supplier } from '@app/model/users/supplier/supplier';
import { OffersService } from '@app/service/offers/offers.service';
import { SupplierService } from '@app/service/supplier/supplier.service';
import { AuthenticationService } from '@app/service/user';

@Component({
  selector: 'app-supplier-all-offers',
  templateUrl: './supplier-all-offers.component.html',
  styleUrls: ['./supplier-all-offers.component.css']
})
export class SupplierAllOffersComponent implements OnInit {
  public supplier : Supplier;
  public offers : Offer[];
  public offerForm : FormGroup;
  public edit : boolean = false;
  public selectedDate : Date;
  public today :Date;
  public displayedColumns: string[] = ['name', 'price', 'offerStatus'];
  public dataSource;

  constructor(private offerService: OffersService, private authenticationService : AuthenticationService, private supplierService : SupplierService, private router:Router) { }

  ngOnInit(): void {
    this.today=new Date();
    this.loadSupplier();

  }
//   applyFilter(filterValue: string){
//     this.dataSource.filter = filterValue.trim().toLowerCase();
// }
  supplierLogout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);
  }

  loadSupplier(){
    this.supplierService.getSupplier(Number(localStorage.getItem('userId'))).subscribe(
      data => 
      { 
        this.supplier = new Supplier(data.name, data.surname, data.dateOfBirth, data.phoneNumber, data.email,data.password,data.address,data.role, data.authorities,data.firmName,data.firstTimeChanged);
        if(!this.supplier.firstTimeChanged){
          this.router.navigate(['/supplier']);
        }
      });
      this.offerService.getAllOffersBySuppllier(Number(localStorage.getItem('userId'))).subscribe(data => 
        {
          this.offers = data;
          this.dataSource = new MatTableDataSource(data);
          console.log(this.offers);
        });

  }

  getBasedOnStatus(event){
    if(event.value==3){
      this.dataSource=this.offers;
    }else{
      this.offerService.findByStatus(event.value, Number(localStorage.getItem('userId'))).subscribe(data =>
        {
          this.dataSource = data;
        });
  }
}
  routeToEditOffers(){
    this.router.navigate(['/supplier/offers']);
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
      'price' : new FormControl(offer.price, Validators.required),
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