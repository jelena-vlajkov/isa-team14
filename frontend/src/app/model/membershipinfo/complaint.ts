import { Patient } from "../users/patient/patient";

export class Complaint{
    private id: Number;
    private patient: Patient;
    private text : String;
    private usertToComplainId : Number;
    private role : String;
    private isAnswered : boolean;
    constructor(id : Number, patient : Patient, text : String, userToComplainId : Number, role : String, isAnswered:boolean){
        this.id = id;
        this.patient = patient;
        this.text = text;
        this.usertToComplainId = userToComplainId;
        this.role = role;
        this.isAnswered = isAnswered;
    }
}