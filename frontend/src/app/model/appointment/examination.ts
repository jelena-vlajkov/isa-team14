import {Dermatologist} from "@app/model/users/dermatologist/dermatologist";
import {Period} from "@app/model/appointment/period";
import {Patient} from "@app/model/users/patient/patient";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class Examination {
  public version:Number;
  public id:Number;
  public appointmentPeriod:Period;
  public cost: Number;
  public type:String;
  public isCanceled: boolean;
  public patient:Patient;
  public pharmacy:Pharmacy;
  public finished:Boolean;
  public dermatologist: Dermatologist;

  constructor(version:Number,id:Number,appointmentPeriod:Period,cost:Number,type:String,isCanceled:boolean
              ,patient:Patient,pharmacy:Pharmacy,finished:boolean,dermatologist:Dermatologist) {
    this.id=id;
    this.appointmentPeriod=appointmentPeriod;
    this.cost=cost;
    this.isCanceled=isCanceled;
    this.patient=patient;
    this.pharmacy=pharmacy;
    this.dermatologist=dermatologist;
    this.version = version;
    this.finished = finished;
    this.type=type;
  }

}
