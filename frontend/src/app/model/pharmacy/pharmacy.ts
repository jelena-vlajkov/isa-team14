
import {Address} from "../address/address";
import {AverageGrade} from "@app/model/users/averageGrade";

export class Pharmacy{
  public id : Number;
  public name: String;
  public description:String;
  public email : String;
  public telephone : Number;
  public address: Address;
  public averageGrade: AverageGrade;
  checked?: boolean;
  public averageGradeCount : Number;

  constructor(id:Number, name : String,description:String,address: Address, average_grade: AverageGrade, email : String, telephone : Number){
    this.id=id;
    this.address=address;
    this.description=description;
    this.name = name;
    this.email = email;
    this.telephone=telephone;
    this.averageGrade=average_grade;
  }
}
