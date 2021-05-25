import {Component, OnInit, ViewChild} from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthenticatedUser} from "../model/users/authenticatedUser";
import {PharmacyAdminService} from "@app/service/pharmacyAdmin/pharmacy-admin.service";

import {Role} from "@app/model/users";
import {Address} from "@app/model/address/address";
import {DermatologistService} from "@app/service/dermatologist/dermatologist.service";
import {valueReferenceToExpression} from "@angular/compiler-cli/src/ngtsc/annotations/src/util";
import {PharmacistService} from "@app/service/pharmacist/pharmacist.service";
import {PharmacyStorageService} from "@app/service/pharmacy-storage/pharmacy-storage.service";
import {ActivatedRoute, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {AppointmentService} from "@app/service/appointment/appointment.service";
import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {AverageGrade} from "@app/model/users/averageGrade";
import {GooglePlacesComponent} from "@app/google-places/google-places.component";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {PharmacyService} from "@app/service/pharmacy/pharmacy.service";
import {PricelistService} from "@app/service/pricelist/pricelist.service";
import {Pricelist} from "@app/model/pharmacy/pricelist";
import {PromotionsService} from "@app/service/promotions/promotions.service";
import {Promotion} from "@app/model/promotions/promotion";
import {Medication} from "@app/model/medications/medication";
import {Period} from "@app/model/appointment/period";
import {AuthenticationService} from "@app/service/user";


@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {
  name:String;
  address:String;
  grade:Number;
  about:String;
  currentUserId:String;
  email:String;
  telephone:Number;
  dermatologists: Dermatologist[]=new Array();
  pharmacists: String[]=new Array();
  pharmacyId:Number;
  medications:String[]=new Array();
  private StringIsNumber = value => isNaN(Number(value)) === false;
  availableAppointments:String[] = new Array();
  @ViewChild(GooglePlacesComponent) googleplaces;
  pharmacy:Pharmacy;
  editProfileForm: FormGroup;
  pricelist:String[]=new Array();
  pharmacyPricelist:Pricelist[]=new Array();
  pharmacyPromotions:Promotion[]=new Array();
  medicationsNotInPharmacy:Medication[]=new Array();
  public showPromotions=false;
  public addPromotion=false;
  public profile:boolean = true;
  public edit:boolean = false;
  public showPricelist = false;
  public changePassword:boolean = false;
  public scheduleAppointment:boolean=false;
  public addPricelistEntityDialog:boolean = false;
  addPricelistEntityFormGroup:FormGroup;
  public isPharmacyAdmin:boolean = false;


  constructor(private pharmacyAdminService:PharmacyAdminService
              ,private dermatologistService:DermatologistService
              ,private pharmacistService:PharmacistService
              ,private pharmacyStorageService:PharmacyStorageService
              ,private appointmentService:AppointmentService
              ,private pharmacyService:PharmacyService
              ,private router:Router
              ,private pricelistService:PricelistService
              ,private promotionsService:PromotionsService
              ,private authenticationService:AuthenticationService) { }

  ngOnInit(): void {
    this.currentUserId = localStorage.getItem('userId');
    console.log(localStorage.getItem('userRole'));
    if(localStorage.getItem('userRole') == "PharmacyAdmin"){
      this.isPharmacyAdmin = true;
    }


    this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
      result => {
        this.pharmacy=result;
        this.pharmacyId = result.id;
        this.name = result.name;
        this.grade=this.countAverageGrade(result.averageGrade);
        this.about = result.description;
        this.email=result.email;
        this.telephone=result.telephone;
        this.address = result.address.street + ", " + result.address.city.name + ", " + result.address.state.name;


        this.pharmacyStorageService.getByPharmacy(this.pharmacyId).subscribe(
          result=>{
            result=this.ToArray(result);
            for(let i=0;i<result.length;i++)
            {
              this.medications.push(result[i].medicationName);
            }

          });

        this.getMedicationsNotInPharmacy();

        this.getPromotionsByPharmacy();

        this.dermatologistService.getDermatologistsByPharmacy(this.pharmacyId).subscribe(
          result => {
            result=this.ToArray(result);
            for(let i=0;i<result.length;i++)
            {
              this.dermatologists.push(result[i]);
            }
            for(let i=0;i<this.dermatologists.length;i++){
              this.appointmentService.getAvailableAppointmentsForDermatologists(this.dermatologists[i].id,this.pharmacyId).subscribe(result=>
              {
                result=this.ToArray(result);
                for(let j=0;j<result.length;i++) {
                  let startTime = new Date(result[j].appointmentPeriod.startTime.valueOf());
                  let endTime = new Date(result[j].appointmentPeriod.endTime.valueOf());
                  this.availableAppointments.push(
                    this.dermatologists[i].name + " " + this.dermatologists[i].surname
                    + ", " + startTime.getHours()
                    + ":" + startTime.getMinutes()
                    + "-" + endTime.getHours()
                    + ":" + endTime.getMinutes()
                    + " " + endTime.getDate() + "."+endTime.getMonth()+"."+endTime.getFullYear()+".");
                }
              });
            }
          });
        this.pharmacistService.getPharmacistsByPharmacy(this.pharmacyId).subscribe(
          result => {
            result=this.ToArray(result);
            for(let i=0;i<result.length;i++)
            {
              this.pharmacists.push(result[i].name+" "+result[i].surname);
            }
          });
       });


        this.editProfileForm = new FormGroup({
          'name' : new FormControl(null, Validators.required),
          'description' : new FormControl(null, Validators.required)
        });

        this.addPricelistEntityFormGroup=new FormGroup({
          'medication': new FormControl(null,Validators.required),
          'startDate':new FormControl(null,Validators.required),
          'endDate':new FormControl(null,Validators.required),
          'price':new FormControl(null,Validators.required)});

      }

  getMedicationsNotInPharmacy(){
    this.pharmacyStorageService.getMedicationsNotInPharmacy(this.pharmacyId).subscribe(
      result=>{
        result=this.ToArray(result);
        this.medicationsNotInPharmacy = result;

      });
  }


  ToArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }

  addAdmin(){}
  countAverageGrade(pharmacyAverageGrade:AverageGrade):number{
    let grade=((5 * pharmacyAverageGrade.excellent.valueOf()) + (4 * pharmacyAverageGrade.veryGood.valueOf())
      + (3 * pharmacyAverageGrade.good.valueOf()) + (2 * pharmacyAverageGrade.poor.valueOf())
      + (1 * pharmacyAverageGrade.veryPoor.valueOf()))
      / (pharmacyAverageGrade.excellent.valueOf() + pharmacyAverageGrade.veryGood.valueOf()
        + pharmacyAverageGrade.good.valueOf() + pharmacyAverageGrade.poor.valueOf()
        + pharmacyAverageGrade.veryPoor.valueOf());
    return grade;
  }
  showEditProfileDialog(){
      this.showPromotions = false;
      this.addPromotion = false;
      this.profile = false;
      this.edit = true;
      this.showPricelist = false;
      this.changePassword = false;
      this.scheduleAppointment=false;
      this.addPricelistEntityDialog = false;

  }

  getPromotionsByPharmacy(){
    this.promotionsService.getPromotionsByPharmacy(this.pharmacyId).subscribe(result =>{
      result=this.ToArray(result);
      this.pharmacyPromotions=result;
    });
  }

  editProfile(){
    this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
      result => {
        if(this.googleplaces.address==undefined){
          alert('Please enter address using location picker. Just start typing and pick your address from combobox');
        }else{
        this.pharmacy=new Pharmacy(result.id,this.editProfileForm.value.name,result.description
          ,this.googleplaces.address
          ,result.averageGrade,result.email,result.telephone);
        }
        this.pharmacyService.editPharmacy(this.pharmacy).subscribe(result=>{
          this.router.navigate(['/pharmacy-profile']);

        });

      });

   }

  showPharmacyPricelist(){
    this.addPricelistEntityDialog = false;
    this.showPricelist = true;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
    this.pharmacyPricelist=new Array();
    this.pharmacyStorageService.getByPharmacy(this.pharmacyId).subscribe(result=>
    {
      result=this.ToArray(result);
      console.log("length:"+result.length);
      for(let i=0;i<result.length;i++){
          this.pricelistService.getPricelistByMedicationAndPharmacy(result[i].medicationCode,result[i].pharmacy.id).subscribe(result => {
            if(result != null){
              this.pricelist.push(result.medication.name+"         "+result.price+"din");
              this.pharmacyPricelist.push(result);
            }
          });
      }
    });
  }
  changeValue(pricelistEntityId:any){
    this.pharmacyPricelist[this.pharmacyPricelist.findIndex(x => x.id===pricelistEntityId)].price = Number(document.getElementById(pricelistEntityId).innerText);
  }
  editPricelist(){
    let enabled=true;
    for(let i=0;i<this.pharmacyPricelist.length;i++){
      if(isNaN(Number(this.pharmacyPricelist[i].price))){
        enabled=false;
        alert("Input prices must be number.Check if your inputs are valid.");
      }
    }
    if(enabled){
      this.pricelistService.editPricelistEntity(this.pharmacyPricelist).subscribe(result=>{});
      this.showPricelist = false;
      this.profile = true;
      this.edit = false;
      this.changePassword = false;
      this.addPromotion = false;
      this.showPromotions=false;
      this.scheduleAppointment=false;
      this.pharmacyPricelist=new Array();
      this.addPricelistEntityDialog = false;
    }

  }

  showPromotionsDialog(){
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=true;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
  }
  cancelEdit(){
    this.showPricelist = false;
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
  }

  cancelScheduling(){
    this.showPricelist = false;
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
  }

  showScheduleAppointment(){
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=true;
    this.addPricelistEntityDialog = false;
  }

  addPromotionClicked(){
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = true;
    this.showPromotions=false;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
  }

  addPromotionSubmitted(){
    let promotion=new Promotion(null
      ,(<HTMLInputElement>document.getElementById('promotionDescription')).value
      ,(<HTMLInputElement>document.getElementById('promotionStartTime')).valueAsDate
      ,(<HTMLInputElement>document.getElementById('promotionEndTime')).valueAsDate
      ,this.pharmacy);
    this.promotionsService.addPromotion(promotion).subscribe(result=>{
      this.getPromotionsByPharmacy();
      this.showPricelist = false;
      this.profile = false;
      this.edit = false;
      this.changePassword = false;
      this.addPromotion = false;
      this.showPromotions=true;
      this.scheduleAppointment=false;
      this.addPricelistEntityDialog = false;
    });

  }

  addPromotionCanceled(){
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=true;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
  }


  addPricelistClicked() {
    this.getMedicationsNotInPharmacy();
    this.addPricelistEntityDialog = true;
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
  }

  addPricelistSubmitted() {
    this.addPricelistEntityDialog = false;
    this.showPricelist = true;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
    let pricelist=new Pricelist(null
                                ,this.addPricelistEntityFormGroup.value.medication
                                ,this.addPricelistEntityFormGroup.value.price
                                ,this.addPricelistEntityFormGroup.value.startDate
                                ,this.addPricelistEntityFormGroup.value.endDate
                                ,this.pharmacy);
    this.pricelistService.addPricelistEntity(pricelist).subscribe(result => {
      this.showPharmacyPricelist();
      this.addPricelistEntityFormGroup.value.price = null;
      this.addPricelistEntityFormGroup.value.startDate = null;
      this.addPricelistEntityFormGroup.value.endDate = null;
    });

  }

  addPricelistCanceled() {
    this.showPharmacyPricelist();
  }

  deletePricelistEntity(pricelistEntityId: Number) {
    console.log("pricelist entity id:"+pricelistEntityId);
    this.pricelistService.deletePricelist(pricelistEntityId).subscribe(result=>{
      this.showPharmacyPricelist();
    });
  }

  checkLoggedInUser(){
    return this.authenticationService.getUserValue();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
