import { Address } from "@app/model/address/address";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";
import { Gender } from "../patient/gender";
import { Role } from "../role";
import {AverageGrade} from "@app/model/users/averageGrade";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class Pharmacist {
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
    public pharmacy : Pharmacy;
    public averageGrade : Number;

    constructor(id:Number,name:String, surname : String, dateOfBirth: Date
                , phoneNumber : String, email : String, password : String
                , gender: Gender, address : Address, role : Role
                , autorities : Number[], pharmacy: Pharmacy
                , licenceNumber :String,firstTimeChanged : boolean){
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
        this.pharmacy = pharmacy;
       // this.averageGrade = averageGrade;
    }
}
