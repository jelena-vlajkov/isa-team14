
export class MedicationOrder{

  public medicationId:Number;
  public medicationName:String;
  public quantity:Number;

  constructor(medicationId:Number, medicationName:String,quantity:Number){
    this.medicationId=medicationId;
    this.medicationName=medicationName;
    this.quantity=quantity;

  }
}
