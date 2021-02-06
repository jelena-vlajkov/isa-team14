import { Pharmacy } from "../pharmacy/pharmacy";
import { Patient } from "../users/patient/patient";

export class PharmacyComplaint{
    public id: Number;
    public patient: Patient;
    public text : String;
    public pharmacy : Pharmacy;
    public role : String;
    public isAnswered : boolean;
    constructor(id : Number, patient : Patient, text : String, pharmacy : Pharmacy, role : String, isAnswered:boolean){
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.pharmacy = pharmacy;
        this.role = role;
        this.isAnswered = isAnswered;
    }
}