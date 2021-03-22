import { Pharmacy } from "../pharmacy/pharmacy";
import { Patient } from "../users/patient/patient";

export class Subscription{
    public patient : Patient;
    public pharmacy : Pharmacy;

    constructor(patient : Patient, pharmacy : Pharmacy){
        this.patient = patient;
        this.pharmacy = pharmacy;
    }
}