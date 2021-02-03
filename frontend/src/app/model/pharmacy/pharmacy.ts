import {Address} from "../address/address";

export class Pharmacy{
  public name: String;
  public id: number ;
  public description:String;
  public address: Address;
  public  average_grade: number;
  constructor(name : String,id: number,description:String,address: Address,average_grade: number){
    this.name = name;
    this.id=id;
    this.address=address;
    this.average_grade=average_grade;
    this.description=description;
  }
}
