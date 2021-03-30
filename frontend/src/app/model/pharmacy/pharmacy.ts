import {Address} from "../address/address";

export class Pharmacy{
  public id : Number;
  public name: String;
  public description:String;
  public email : String;
  public telephone : Number;
  public address: Address;
  public average_grade: Number;
  checked?: boolean;

  constructor(id:Number, name : String,description:String,email : String, telephone : Number,address: Address, average_grade: Number){
    this.id=id;
    this.address=address;
    this.description=description;
    this.name = name;
    this.email = email;
    this.telephone=telephone;
    this.average_grade=average_grade;
  }
}
