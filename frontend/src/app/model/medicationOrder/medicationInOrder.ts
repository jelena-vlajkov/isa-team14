import {OrderedMedication} from "@app/model/medicationOrder/orderedMedication";

;
import {Order} from "@app/model/medicationOrder/order";

export class MedicationInOrder {
  public id:Number;
  public orderedMedication : OrderedMedication;
  public order : Order;

  constructor(id:Number,orderedMedication:OrderedMedication,order:Order){
    this.id = id;
    this.order = order;
    this.orderedMedication = orderedMedication;
  }
}
