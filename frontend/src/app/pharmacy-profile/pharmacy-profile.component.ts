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
import {DrugReservationsService} from "@app/service/drug-reservations/drug-reservations.service";
import { AgmCoreModule } from '@agm/core';
import {Examination} from "@app/model/appointment/examination";

@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css'],
})
export class PharmacyProfileComponent implements OnInit {
  grade:Number;
  currentUserId:String;
  dermatologists: Dermatologist[]=new Array();
  pharmacists: String[]=new Array();
  pharmacyId:Number;
  medications:String[]=new Array();
  viewPricelist:boolean=false;
  private StringIsNumber = value => isNaN(Number(value)) === false;
  availableAppointments:Examination[] = new Array();
  @ViewChild(GooglePlacesComponent) googleplaces;
  pharmacy:Pharmacy;
  editProfileForm: FormGroup;
  pharmacyPricelist:Pricelist[]=new Array();
  pharmacyPromotions:Promotion[]=new Array();
  medicationsByPharmacy:Medication[]=new Array();
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
  showCurrentPricelistForPharmacy:boolean=false;
  currentPharmacyPricelist:Pricelist[] = new Array();
  showEditPricelistEntity:boolean = false;
  editPricelistEntityFormGroup:FormGroup;
  private pricelistEntityToUpdate: Pricelist;
  currentDate:Date = new Date();


  constructor(private pharmacyAdminService:PharmacyAdminService
              ,private dermatologistService:DermatologistService
              ,private pharmacistService:PharmacistService
              ,private pharmacyStorageService:PharmacyStorageService
              ,private appointmentService:AppointmentService
              ,private pharmacyService:PharmacyService
              ,private router:Router
              ,private pricelistService:PricelistService
              ,private promotionsService:PromotionsService
              ,private authenticationService:AuthenticationService
              ,private drugReservationsService:DrugReservationsService) {

  }

  ngOnInit(): void {
    this.currentUserId = localStorage.getItem('userId');
    if (localStorage.getItem('userRole') == "PharmacyAdmin") {
      this.isPharmacyAdmin = true;
      this.pharmacyAdminService.getPharmacyByAdmin(Number(this.currentUserId)).subscribe(
        result => {
          this.pharmacy = result;
          this.pharmacyId = result.id;
          this.grade = this.countAverageGrade(result.averageGrade);
          this.getPharmacyStorage();
          this.getDermatologistsByPharmacy();
          this.getAvailableAppointmentsForDermatologist();
          this.getPharmacistsByPharmacy();
        });
    }

    else{
      this.pharmacyService.getPharmacyById(Number(localStorage.getItem('pharmacyId'))).subscribe(result =>{
        this.pharmacy = result;
        this.pharmacyId = result.id;
        this.grade = this.countAverageGrade(result.averageGrade);
        this.getPharmacyStorage();
        this.getDermatologistsByPharmacy();
        this.getAvailableAppointmentsForDermatologist();
        this.getPharmacistsByPharmacy();
      });
    }


  }



  ToArray(enumme) {
    return Object.keys(enumme)
      .filter(this.StringIsNumber)
      .map(key => enumme[key]);
  }
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
    this.editProfileForm = new FormGroup({
      'name' : new FormControl(this.pharmacy.name, Validators.required),
      'description' : new FormControl(this.pharmacy.description, Validators.required),
      'telephone' : new FormControl(this.pharmacy.telephone,[ Validators.required,Validators.pattern("^[0-9]*$")]),
      'email' : new FormControl(this.pharmacy.email, [Validators.email,Validators.required]),
    });

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
      console.log(this.pharmacyPromotions);
    });
  }

  editProfile(){
    console.log(this.googleplaces.address);
    if(this.googleplaces.address===undefined){
      alert('Please enter address using location picker. Just start typing and pick your address from combobox');
    }else {
      this.pharmacy = new Pharmacy(this.pharmacy.id, this.editProfileForm.value.name, this.editProfileForm.value.description
        , this.googleplaces.address, this.pharmacy.averageGrade, this.editProfileForm.value.email, this.editProfileForm.value.telephone);

      this.pharmacyService.editPharmacy(this.pharmacy).subscribe(result => {
        this.edit = false;
        this.profile = true;
        console.log(this.pharmacy);


      });
    }
   }

  showPharmacyPricelist(){
    if(this.isPharmacyAdmin){
      this.addPricelistEntityDialog = false;
      this.showPricelist = true;
      this.profile = false;
      this.edit = false;
      this.changePassword = false;
      this.addPromotion = false;
      this.showPromotions = false;
      this.scheduleAppointment = false;
      this.showCurrentPricelistForPharmacy = false;
      this.showEditPricelistEntity = false;
      this.pricelistService.getAllByPharmacy(this.pharmacyId).subscribe(result => {
        this.pharmacyPricelist = result;
      });
    }
    else{
      this.showCurrentPricelist();
    }

  }



  showPromotionsDialog(){
    this.showCurrentPricelistForPharmacy = false;
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=true;
    this.scheduleAppointment=false;
    this.addPricelistEntityDialog = false;
    this.getPromotionsByPharmacy();
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
    this.getAvailableAppointmentsForDermatologist();
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
    this.getMedicationByPharmacy();
    this.addPricelistEntityFormGroup=new FormGroup({
      'medication': new FormControl(null,Validators.required),
      'startDate':new FormControl(null,Validators.required),
      'endDate':new FormControl(null,Validators.required),
      'price':new FormControl(null,Validators.required)});

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
    let pricelist=new Pricelist(null
                                ,this.addPricelistEntityFormGroup.value.medication
                                ,this.addPricelistEntityFormGroup.value.price
                                ,this.addPricelistEntityFormGroup.value.startDate
                                ,this.addPricelistEntityFormGroup.value.endDate
                                ,this.pharmacy);
    this.pricelistService.addPricelistEntity(pricelist).subscribe(result => {
      if(result){
        this.showPharmacyPricelist();
        this.addPricelistEntityDialog = false;
        this.showPricelist = true;
        this.profile = false;
        this.edit = false;
        this.changePassword = false;
        this.addPromotion = false;
        this.showPromotions=false;
        this.scheduleAppointment=false;
      }
      else{
        console.log("neuspesno");
        alert("Can't add pricelist for selected period.You can edit pricelist" +
          " entity or delete first pricelist entity for particular period.");
      }

    });

  }

  addPricelistCanceled() {
    this.showPharmacyPricelist();
  }

  deletePricelistEntity(pricelistEntityId: Number,medicationId:Number) {
    this.drugReservationsService.isDrugReserved(medicationId,this.pharmacyId).subscribe(result=>{
      if(result==false){
        this.pricelistService.deletePricelist(pricelistEntityId).subscribe(result=>{
          this.showPharmacyPricelist();
        });
      }
      else{
        alert("Can't delete medication from pharmacy,there is active drug reservation for this medication.");
      }
    });

  }

  checkLoggedInUser(){
    return this.authenticationService.getUserValue();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }


  showCurrentPricelist() {
    this.showCurrentPricelistForPharmacy = true;
    this.showPricelist = false;
    this.addPricelistEntityDialog = false;
    this.showEditPricelistEntity = false;
    this.addPricelistEntityDialog = false;
    this.showPricelist = false;
    this.profile = false;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;

    this.currentPharmacyPricelist=new Array();
    this.pharmacyStorageService.getByPharmacy(this.pharmacyId).subscribe(result=>
    {
      result=this.ToArray(result);
      console.log(result);
      for(let i=0;i<result.length;i++){
        this.pricelistService.getPricelistByMedicationAndPharmacy(result[i].medicationCode,result[i].pharmacy.id).subscribe(result => {
          if(result!=null){
            this.currentPharmacyPricelist.push(result);
          }
        });
        console.log(this.currentPharmacyPricelist);
      }
    });
  }

  editPricelistEntity(pricelist: Pricelist) {
    this.showCurrentPricelistForPharmacy = false;
    this.showPricelist = false;
    this.addPricelistEntityDialog = false;
    this.showEditPricelistEntity=true;
    this.pricelistEntityToUpdate=pricelist;
    this.editPricelistEntityFormGroup=new FormGroup({
      'medication': new FormControl(pricelist.medication.name,null),
      'startDate':new FormControl(pricelist.startPeriod,null),
      'endDate':new FormControl(pricelist.endPeriod,null),
      'price':new FormControl(pricelist.price,Validators.required)});

  }


  editPricelistSubmitted() {
    let pricelistEntity=new Pricelist(this.pricelistEntityToUpdate.id
      ,this.pricelistEntityToUpdate.medication,this.editPricelistEntityFormGroup.value.price,
      this.editPricelistEntityFormGroup.value.startDate,this.editPricelistEntityFormGroup.value.endDate,this.pricelistEntityToUpdate.pharmacy);
    this.pricelistService.editPricelistEntity(pricelistEntity).subscribe(result=>{
      this.showPharmacyPricelist();
    });

  }

  private getPharmacyStorage() {
    this.pharmacyStorageService.getByPharmacy(this.pharmacyId).subscribe(
      result=>{
        result=this.ToArray(result);
        for(let i=0;i<result.length;i++)
        {
          this.medications.push(result[i].medicationName);
        }

      });
  }

  private getAvailableAppointmentsForDermatologist() {
    for(let i=0;i<this.dermatologists.length;i++) {
      this.appointmentService.getAvailableAppointmentsForDermatologists(this.dermatologists[i].id, this.pharmacyId).subscribe(result => {
        result = this.ToArray(result);
        for (let j = 0; j < result.length; j++) {
            result[j].dermatologist = this.dermatologists[i];
            this.availableAppointments.push(result[j]);
        }
      });
    }
    console.log(this.availableAppointments);

  }

  private getPharmacistsByPharmacy() {
    this.pharmacistService.getPharmacistsByPharmacy(this.pharmacyId).subscribe(
      result => {
        result=this.ToArray(result);
        for(let i=0;i<result.length;i++)
        {
          this.pharmacists.push(result[i].name+" "+result[i].surname);
        }
      });
  }

  private getMedicationByPharmacy() {
    this.pharmacyStorageService.getMedicationsInPharmacy(this.pharmacyId).subscribe(result =>{
      console.log("rez: "+result);
      this.medicationsByPharmacy = result;
    });
    console.log(this.medicationsByPharmacy);
  }

  private getDermatologistsByPharmacy() {
    this.dermatologistService.getDermatologistsByPharmacy(this.pharmacyId).subscribe(
      result => {
        result = this.ToArray(result);
        for (let i = 0; i < result.length; i++) {
          this.dermatologists.push(result[i]);
        }
      });
  }

  backToPharmacyProfile() {
    this.showCurrentPricelistForPharmacy = false;
    this.showPricelist = false;
    this.addPricelistEntityDialog = false;
    this.showEditPricelistEntity = false;
    this.addPricelistEntityDialog = false;
    this.showPricelist = false;
    this.profile = true;
    this.edit = false;
    this.changePassword = false;
    this.addPromotion = false;
    this.showPromotions=false;
    this.scheduleAppointment=false;
  }
}
