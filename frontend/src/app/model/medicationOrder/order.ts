import {MedicationInOrder} from "@app/model/medicationOrder/medicationInOrder";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {OrderedMedication} from "@app/model/medicationOrder/orderedMedication";

export class Order{
  public id:Number;
  public dueDate:Date;
  public orderedMedications:OrderedMedication[];
  public pharmacy:Pharmacy;
  public uniqueidentifier:Number;

  constructor(id:Number, orderedMedications:OrderedMedication[], dueDate:Date, pharmacy:Pharmacy, uniqueidentifier:Number){
    this.id=id;
    this.dueDate=dueDate;
    this.orderedMedications=orderedMedications;
    this.pharmacy=pharmacy;
    this.uniqueidentifier=uniqueidentifier;
  }
}

