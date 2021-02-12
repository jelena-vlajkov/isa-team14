import { SystemAdmin } from "../users/systemAdmin/systemAdmin";
import { Complaint } from "./complaint";

export class AnswerToComplaint{
    public id : Number;
    public complaint : Complaint;
    public answer : String;
    public systemAdmin : SystemAdmin;
    
    constructor(id : Number, complaint: Complaint, answer: String, systemAdmin : SystemAdmin){
        this.id = id;
        this.complaint = complaint;
        this.answer = answer;
        this.systemAdmin = systemAdmin;
    }
}