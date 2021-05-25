import { Address } from "@app/model/address/address";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";
import { Gender } from "../users/patient/gender";
import { Role } from "../users/role";

export class UpdateEmployee{
    public name : String;
    public surname : String;
    public dateOfBirth : Date;
    public phoneNumber : String;
    public email : String;
    public gender : String;
    public address : Address;
    constructor() {}
}