import {Address} from "../address/address";

export class Pharmacy{
  public id : Number;
  public name: String;
  public description:String;
  public address: Address;
  public  average_grade: Number;
  checked?: boolean;

  constructor(id:Number, name : String,description:String,address: Address, average_grade: Number){
    this.id=id;
    this.address=address;
    this.description=description;
    this.name = name;
  }
}
