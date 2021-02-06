import {MedicationOrder} from "@app/model/medicationOrder/medicationOrder";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class Order{
  public id:Number;
  public orderedMedications:MedicationOrder[];
  public dueDate:Date;
  public pharmacy:Pharmacy;


  constructor(id:Number,orderedMedications:MedicationOrder[],dueDate:Date,pharmacy:Pharmacy){
    this.id=id;
    this.dueDate=dueDate;
    this.orderedMedications=orderedMedications;
    this.pharmacy=pharmacy;
  }
}

