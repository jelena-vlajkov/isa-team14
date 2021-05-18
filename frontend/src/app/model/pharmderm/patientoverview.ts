import { Gender } from "../users/patient/gender";
import { AppointmentOverview } from "./appointmentoverview";

export class PatientsOverview{
    public name : String;
    public surname : String;
    public patientId : Number;
    public prescribedDrugs : String[];
    public previousAppointments : AppointmentOverview[];
    public dateOfBirth : Date;
    public gender : Gender
    constructor(name : string, surname : string, patientId : Number, prescribedDrugs : string[], previousAppointments : AppointmentOverview[], dateOfBirth : Date, gender : Gender) {
        name = name;
        gender = gender;
        dateOfBirth = dateOfBirth;
        surname = surname;
        patientId = patientId;
        prescribedDrugs = prescribedDrugs;
        previousAppointments = previousAppointments;
    }

}