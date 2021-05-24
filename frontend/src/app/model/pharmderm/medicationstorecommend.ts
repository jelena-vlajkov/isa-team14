import { Gender } from "../users/patient/gender";
import { AppointmentOverview } from "./appointmentoverview";

export class MedicationsToRecommend {
    public name : String;
    public id : Number;
    public available : boolean;
    public prescribed : boolean;
    public days : Number;
    
    constructor() {
        this.prescribed = false;
    }

}