
export class MedicationOrder{

  public medicationId:Number;
  public quantity:Number;
  public medicationName:String;

  constructor(medicationId:Number,quantity:Number,medicationName:String){
    this.medicationId=medicationId;
    this.quantity=quantity;
    this.medicationName=medicationName;

  }
}
