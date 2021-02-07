import { SystemAdmin } from "../users/systemAdmin/systemAdmin";
import { Complaint } from "./complaint";
import { DermatologistComplaint } from "./dermatologistComplaint";

export class AnswerToDermComplaint{
    public id : Number;
    public complaint : DermatologistComplaint;
    public answer : String;
    public systemAdmin : SystemAdmin;
    
    constructor(id : Number, complaint: DermatologistComplaint, answer: String, systemAdmin : SystemAdmin){
        this.id = id;
        this.complaint = complaint;
        this.answer = answer;
        this.systemAdmin = systemAdmin;
    }
}