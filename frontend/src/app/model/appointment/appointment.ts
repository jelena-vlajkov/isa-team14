export class Appointment{
    public id : Number;
    public startTime :Date;
    public endTime : Date; 
    public appointmentCost : Number;
    public appointmentType : String;
    public patientName : String;
    public canceled : boolean;
    public patientEmail : String;
    public medicalStaffName : String;
    public medicalStaffEmail : String;

    constructor(startTime : Date, endTime : Date, appointmentCost : Number, appointmentType : String, patientName : String, canceled : boolean, 
        patientEmail : String, medicalStaffName : String, medicalStaffEmail : String ){
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentCost = appointmentCost;
        this.appointmentType = appointmentType;
        this.patientEmail = patientEmail;
        this.patientName = patientName;
        this.canceled = canceled;
        this.medicalStaffEmail = medicalStaffEmail;
        this.medicalStaffName = medicalStaffName;
    }
    
}