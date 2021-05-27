import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class VacationRequest {
    public id : Number;
    public startDate : Date;
    public endDate : Date;
    public medicalStaff : MedicalStaff;
    public vacationReason : String;
    public pharmacy : Pharmacy;


    constructor(id : Number,startDate : Date,endDate : Date
                ,medicalStaff : MedicalStaff, vacationReason : String , pharmacy : Pharmacy) {
      this.id = id;
      this.startDate = startDate;
      this.endDate = endDate;
      this.medicalStaff = medicalStaff;
      this.vacationReason = vacationReason;
      this.pharmacy = pharmacy;
    }
}
