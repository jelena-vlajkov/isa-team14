import { Dermatologist } from "../users/dermatologist/dermatologist";
import { Patient } from "../users/patient/patient";

export class DermatologistComplaint{
    public id: Number;
    public patient: Patient;
    public text : String;
    public dermatologist : Dermatologist;
    public role : String;
    public isAnswered : boolean;
    constructor(id : Number, patient : Patient, text : String, dermatologist : Dermatologist, role : String, isAnswered:boolean){
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.dermatologist = dermatologist;
        this.role = role;
        this.isAnswered = isAnswered;
    }
}