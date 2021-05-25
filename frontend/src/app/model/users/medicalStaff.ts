import {User} from "@app/model/users/user";
import {Gender} from "@app/model/users/patient/gender";
import {Address} from "@app/model/address/address";
import {Role} from "@app/model/users/role";
import {Pharmacy} from "@app/model/pharmacy/pharmacy";

export class MedicalStaff {
  public id:Number;
  public name : String;
  public surname : String;
  public dateOfBirth : Date;
  public phoneNumber : String;
  public email : String;
  public password : String;
  public gender : Gender;
  public address : Address;
  public role : Role;
  public authorities : Number[];
  public firstTimeChanged : boolean;
  public licenceNumber : String

  constructor(id:Number, name:String, surname : String, dateOfBirth: Date
              , phoneNumber : String, email : String, password : String
              , gender: Gender, address : Address, role : Role, autorities : Number[]
              , firstTimeChanged:boolean,licenceNumber:String){
    this.id=id;
    this.name = name;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.address = address;
    this.role = role;
    this.authorities = autorities;
    this.firstTimeChanged = firstTimeChanged;
    this.licenceNumber = licenceNumber;
  }

}
