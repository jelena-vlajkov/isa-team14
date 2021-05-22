// private Long id;
// private Long price;
// private MedicationDTO medication;
// private PharmacyDTO pharmacy;
// private Period period;

import { Medication } from "../medications/medication";
import { Pharmacy } from "./pharmacy";
import {Period} from "./../appointment/period"
export class Pricelist{
    public id : Number;
    public price : Number;
    public medication : Medication;
    public pharmacy : Pharmacy;
    public startPeriod : Date;
    public endPeriod : Date;

    constructor(id:Number,medication:Medication,price:Number,startPeriod:Date,endPeriod:Date,pharmacy:Pharmacy) {
      this.id = id;
      this.medication = medication;
      this.pharmacy = pharmacy;
      this.price = price;
      this.startPeriod = startPeriod;
      this.endPeriod = endPeriod;

    }
}
