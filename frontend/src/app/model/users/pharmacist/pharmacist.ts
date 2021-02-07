import { Address } from "@app/model/address/address";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";
import { Gender } from "../patient/gender";
import { Role } from "../role";

export class Pharmacist{
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
    public pharmacy : Pharmacy;

    constructor(id:Number,name:String, surname : String, dateOfBirth: Date, phoneNumber : String, email : String, password : String, gender: Gender, address : Address, role : Role, autorities : Number[], pharmacy: Pharmacy){
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
        this.pharmacy = this.pharmacy;
    }
}