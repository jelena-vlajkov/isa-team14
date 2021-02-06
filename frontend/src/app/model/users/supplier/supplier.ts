import {Gender} from "../patient/gender";
import {Address} from "../../address/address";
import {Role} from "../role";
import { Pharmacy } from "../../pharmacy/pharmacy";

export class Supplier{
  public name : String;
  public surname : String;
  public dateOfBirth : Date;
  public phoneNumber : String;
  public email : String;
  public password : String;
  public address : Address;
  public role : Role;
  public authorities : Number[];
  public firmName : String;
  public firstTimeChanged : boolean;

  constructor(name:String, surname : String, dateOfBirth: Date, phoneNumber : String, email : String, password : String,  address : Address, role : Role, autorities : Number[],  firmName : String,firstTimeChanged : boolean){
    this.name = name;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.address = address;
    this.role = role;
    this.authorities = autorities;
    this.firmName = firmName;
    this.firstTimeChanged = firstTimeChanged;
  }
}
