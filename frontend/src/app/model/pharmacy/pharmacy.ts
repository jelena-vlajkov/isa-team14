import {Address} from "../address/address";

export class Pharmacy{
  public id : Number;
  public name: String;
  public description:String;
  public address: Address;
  public average_grade: Number;
  public email : String;
  public telephone : Number;
  checked?: boolean;
  public averageGradeCount : Number;

  constructor(id:Number, name : String,description:String,address: Address, average_grade: Number, email : String, telephone : Number){
    this.id=id;
    this.address=address;
    this.description=description;
    this.name = name;
    this.email = email;
    this.telephone=telephone;
    this.average_grade=average_grade;
  }
}
