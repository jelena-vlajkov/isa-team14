import {MedicationOrder} from "@app/model/medicationOrder/medicationOrder";

export class Order{
  public id:Number;
  public dueDate:Date;
  public medicationOrder:MedicationOrder;

  constructor(id:Number,dueDate:Date,medicationOrder:MedicationOrder){
    this.id=id;
    this.dueDate=dueDate;
    this.medicationOrder=medicationOrder;
  }
}

