import {Medication} from "@app/model/medications/medication";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class medicationInStorage {
  public medication: Medication;
  public quantity:Number;
  public pharmacy: Pharmacy;

public medicationInStorage(medication:Medication ,quantity:Number, pharmacy:Pharmacy ) {
  this.medication = medication;
  this.quantity = quantity;
  this.pharmacy = pharmacy;
}
}
