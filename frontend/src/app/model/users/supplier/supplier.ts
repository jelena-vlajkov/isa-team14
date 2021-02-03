import {Gender} from "../patient/gender";
import {Address} from "../../address/address";
import {Role} from "../role";
import { Pharmacy } from "../../pharmacy/pharmacy";

export class Supplier{
  public name : String;
  public phoneNumber : String;
  public email : String;
  public password : String;
  public headquarters : Address;
  public role : Role;
  public authorities : Number[];

  constructor(name:String, phoneNumber : String, email : String, password : String, headquarters : Address, role : Role, autorities : Number[]){
    this.name = name;

    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.headquarters = headquarters;
    this.role = role;
    this.authorities = autorities;
  }
}
