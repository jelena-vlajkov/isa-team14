import {Address} from "../address/address";

export class Pharmacy{
  public name: String;
  public id: number ;
  public description:String;
  private address: Address;
  public  average_grade: Number;
  constructor(name : String,id: number,description:String,address: Address, average_grade: Number){
    this.name = name;
    this.id=id;
    this.address=address;
    this.average_grade=average_grade;
    this.description=description;
  }
}
