import { Medication } from "../medications/medication";
import { MedicationsToRecommend } from "../pharmderm/medicationstorecommend";
import { PrescribeMedication } from "../pharmderm/prescribemeds";

export class Appointment{
    public id : Number;
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
    public availableMedicationsForPatients : MedicationsToRecommend[];
    public medicationsForPatients : MedicationsToRecommend[];
    public pharmacyId : Number;
    public prescribedMedications : String[];
    public availableAppointment : Appointment[]
    public medicalStaffId : Number;
    public finished : boolean;
    public canAddPenalty : boolean;

    constructor() {
        this.prescribedMedications = [];
        this.finished = false;
    }
}