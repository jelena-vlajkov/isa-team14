import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {Medication} from "@app/model/medications/medication";

export class PharmacyStorage{
  public medicationId:Number;
  public medicationName:String;
  public medicationCode:Number;
  public quantity:Number;
  public pharmacy:Pharmacy;

  constructor(medicationId:Number,medicationName:String,medicationCode:Number,quantity:Number,pharmacy:Pharmacy) {
    this.medicationCode=medicationCode;
    this.medicationId=medicationId;
    this.medicationName=medicationName;
    this.quantity=quantity;
    this.pharmacy=pharmacy;
  }
}
