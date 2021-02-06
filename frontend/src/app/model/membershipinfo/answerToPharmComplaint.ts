import { SystemAdmin } from "../users/systemAdmin/systemAdmin";
import { Complaint } from "./complaint";
import { PharmacistComplaint } from "./pharmacistComplaint";

export class AnswerToPharmComplaint{
    public id : Number;
    public complaint : PharmacistComplaint;
    public answer : String;
    public systemAdmin : SystemAdmin;
    
    constructor(id : Number, complaint: PharmacistComplaint, answer: String, systemAdmin : SystemAdmin){
        this.id = id;
        this.complaint = complaint;
        this.answer = answer;
        this.systemAdmin = systemAdmin;
    }
}