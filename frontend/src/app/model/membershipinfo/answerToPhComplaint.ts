import { SystemAdmin } from "../users/systemAdmin/systemAdmin";
import { Complaint } from "./complaint";
import { PharmacyComplaint } from "./pharmacyComplaint";

export class AnswerToPhComplaint{
    public id : Number;
    public complaint : PharmacyComplaint;
    public answer : String;
    public systemAdmin : SystemAdmin;
    
    constructor(id : Number, complaint: PharmacyComplaint, answer: String, systemAdmin : SystemAdmin){
        this.id = id;
        this.complaint = complaint;
        this.answer = answer;
        this.systemAdmin = systemAdmin;
    }
}