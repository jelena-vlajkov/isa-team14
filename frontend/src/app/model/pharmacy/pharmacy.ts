import {Address} from "../address/address";

export class Pharmacy{
  public id : Number;
  public name: String;
  public description:String;
  public address: Address;
  constructor(name : String,id: number,description:String,address: Address,average_grade: number){
    this.name = name;
    this.id=id;
  }
}
