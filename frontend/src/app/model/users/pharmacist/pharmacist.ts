import { Address } from "@app/model/address/address";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";
import { Gender } from "../patient/gender";
import { Role } from "../role";
import {AverageGrade} from "@app/model/users/averageGrade";
import {MedicalStaff} from "@app/model/users/medicalStaff";

export class Pharmacist extends MedicalStaff{
    public pharmacy : Pharmacy;
    public averageGrade : AverageGrade;

    constructor(id:Number,name:String, surname : String, dateOfBirth: Date
                , phoneNumber : String, email : String, password : String
                , gender: Gender, address : Address, role : Role
                , autorities : Number[], pharmacy: Pharmacy
                , licenceNumber :String,averageGrade : AverageGrade,firstTimeChanged : boolean){
        super(id,name,surname,dateOfBirth,phoneNumber,email,password,gender,address,role
          ,autorities,firstTimeChanged,licenceNumber);
        this.pharmacy = pharmacy;
        this.averageGrade = averageGrade;
    }
}
