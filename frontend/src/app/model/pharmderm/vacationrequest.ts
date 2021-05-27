import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class VacationRequest {
    public medicalStaffId : Number;
    public startDate : Date;
    public endDate : Date;
    public vacationReason : String;


    constructor(id : Number,startDate : Date,endDate : Date
                , vacationReason : String) {
      this.medicalStaffId = id;
      this.startDate = startDate;
      this.endDate = endDate;
      this.vacationReason = vacationReason;
    }
}
