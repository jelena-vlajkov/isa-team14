import { Medication } from "../medications/medication";

export class Appointment{
    public startTime :Date;
    public endTime : Date; 
    public appointmentCost : Number;
    public appointmentType : String;
    public patientName : String;
    public canceled : boolean;
    public patientId : Number;
    public patientEmail : String;
    public medicalStaffName : String;
    public medicalStaffEmail : String;
    public startDateString : string;
    public endDateString : string;
    public medicationsForPatients : Medication[];
    
    constructor() {}
}