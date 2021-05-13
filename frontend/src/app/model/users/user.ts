import { Role } from "@app/model/users/role";
import { Address } from "../address/address";

export class User {
    public id: number;
    public email: string;
    public password: string;
    public surname: string;
    public name: string;
    public role:string;
    public token?: string;
    public address : Address;
    public dateOfBirth : Date;
    public gender : string;
    public city : string;
    public phoneNumber : string;
    public firstTimeChanged : boolean;

    constructor(id : number, email : string, password : string, name : string, surname : string, role : string, address : Address, dateOfBirth : Date, gender : string, city : string, phoneNumber : string, firstTimeChanged : boolean) {
        this.id = id;
        this.firstTimeChanged = firstTimeChanged;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.gender = gender;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

}

