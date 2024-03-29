import { Address } from "../../address/address";
import { Gender } from "../patient/gender";
import { Pharmacy } from "../../pharmacy/pharmacy";
import { Role } from "../role";
import {AverageGrade} from "@app/model/users/averageGrade";

export class Dermatologist{
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
    public pharmacies : Pharmacy[];
   // public averageGrade : AverageGrade;
    public averageGrade : Number; 

    constructor(id:Number, name:String, surname : String, dateOfBirth: Date, phoneNumber : String, email : String, password : String, gender: Gender, address : Address, role : Role, autorities : Number[], pharmacies: Pharmacy[],averageGrade:AverageGrade){
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
        this.pharmacies = pharmacies;
        //this.averageGrade = averageGrade;
    }

}
