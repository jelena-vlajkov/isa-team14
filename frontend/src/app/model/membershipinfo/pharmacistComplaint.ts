import { Pharmacy } from "../pharmacy/pharmacy";
import { Patient } from "../users/patient/patient";
import { Pharmacist } from "../users/pharmacist/pharmacist";

export class PharmacistComplaint{
    public id: Number;
    public patient: Patient;
    public text : String;
    public pharmacist : Pharmacist;
    public role : String;
    public isAnswered : boolean;
    constructor(id : Number, patient : Patient, text : String, pharmacist : Pharmacist, role : String, isAnswered:boolean){
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.pharmacist = pharmacist;
        this.role = role;
        this.isAnswered = isAnswered;
    }
}