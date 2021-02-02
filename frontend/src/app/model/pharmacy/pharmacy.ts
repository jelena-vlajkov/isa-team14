import {Address} from "../address/address";

export class Pharmacy{
  public name: String;
  public description:String;
  public address: Address;
  public  average_grade: number;
  constructor(name : String,description:String,address: Address,average_grade: number){
    this.name = name;
    this.address=address;
    this.average_grade=average_grade;
    this.description=description;
  }
}
