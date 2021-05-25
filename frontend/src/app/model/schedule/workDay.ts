import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class WorkDay{
  public id : Number;
  public date:Date;
  public startTime:Date ;
  public endTime:Date ;
  public medicalStaff: MedicalStaff;
  public pharmacy : Pharmacy;

  constructor(id:Number,date:Date,startTime:Date,endTime:Date,medicalStaff:MedicalStaff,pharmacy:Pharmacy) {
    this.id = id;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.medicalStaff = medicalStaff;
    this.pharmacy = pharmacy;
  }
}
