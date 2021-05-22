import { Medication } from "../medications/medication";
import { PrescribeMedication } from "../pharmderm/prescribemeds";

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
    public availableMedicationsForPatients : PrescribeMedication[];
    public medicationsForPatients : Medication[];
    
    constructor() {}
}