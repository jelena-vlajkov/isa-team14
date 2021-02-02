import {Gender} from "./../patient/gender";
import {Address} from "./../address/address";
import {Role} from "./../users/role";
import { Pharmacy } from "../pharmacy/pharmacy";

export class PharmacyAdmin{
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
  public pharmacy : Pharmacy;

  constructor(name:String, surname : String, dateOfBirth: Date, phoneNumber : String, email : String, password : String, gender: Gender, address : Address, role : Role, autorities : Number[], pharmacy:Pharmacy){
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
    this.pharmacy=pharmacy;
  }
}
