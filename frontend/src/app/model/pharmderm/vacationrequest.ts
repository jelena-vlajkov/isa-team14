import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class VacationRequest {
  public id : Number;
  public medicalStaffId: Number;
  public startDate: Date;
  public endDate: Date;
  public vacationReason: String;
  public medicalStaff: MedicalStaff;
  public pharmacy: Pharmacy;


  constructor(id: Number,medicalStaffId:Number, startDate: Date, endDate: Date, vacationReason: String) {
    this.id = id;
    this.medicalStaffId = medicalStaffId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.vacationReason = vacationReason;
  }
}
