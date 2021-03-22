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
    public period : Period;
}