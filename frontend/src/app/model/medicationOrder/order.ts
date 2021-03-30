import {MedicationOrder} from "@app/model/medicationOrder/medicationOrder";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";
import {OrderedMedication} from "@app/model/medications/orderedMedication";

export class Order{
  public id:Number;
  public dueDate:Date;
  public orderedMedications:MedicationOrder[];
  public pharmacy:Pharmacy;
  public editableDue:Date;
  public uniqueidentifier:Number;


  constructor(id:Number,dueDate:Date,orderedMedications:MedicationOrder[],pharmacy:Pharmacy,editableDue:Date
              ,uniqueidentifier:Number){
    this.id=id;
    this.dueDate=dueDate;
    this.orderedMedications=orderedMedications;
    this.editableDue=editableDue;
    this.pharmacy=pharmacy;
    this.uniqueidentifier=uniqueidentifier;
  }
}

