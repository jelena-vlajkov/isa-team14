import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class Promotion{
  public id:Number;
  public description:String;
  public startTime:Date;
  public endTime:Date;
  public pharmacy:Pharmacy;

  constructor(id:Number,description:String,startTime:Date,endTime:Date,pharmacy:Pharmacy) {
    this.id=id;
    this.description=description;
    this.startTime=startTime;
    this.endTime=endTime;
    this.pharmacy=pharmacy;
  }
}
