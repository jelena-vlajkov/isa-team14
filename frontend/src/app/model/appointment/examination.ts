import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {Period} from "@app/model/appointment/period";
import {Patient} from "@app/model/users/patient/patient";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class Examination {
  public id:Number;
  public appointmentPeriod:Period;
  public cost: Number;
  public type: String;
  public isCanceled: boolean;
  public patient:Patient;
  public pharmacy:Pharmacy;
  public dermatologist: Dermatologist;

  constructor(id:Number,appointmentPeriod:Period,cost:Number,type:String,isCanceled:boolean
              ,patient:Patient,pharmacy:Pharmacy,dermatologist:Dermatologist) {
    this.id=id;
    this.appointmentPeriod=appointmentPeriod;
    this.cost=cost;
    this.type=type;
    this.isCanceled=isCanceled;
    this.patient=patient;
    this.pharmacy=pharmacy;
    this.dermatologist=dermatologist;
  }

}
