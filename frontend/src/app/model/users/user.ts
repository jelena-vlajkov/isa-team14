import { Role } from "@app/model/users/role";

export class User {
    public id: number;
    public username: string;
    public password: string;
    public firstName: string;
    public lastName: string;
    public role:string;
    public token?: string;
    public address : string;
    public dateOfBirth : Date;
    public gender : string;
    public city : string;
    public phoneNumber : string;

    constructor(id : number, username : string, password : string, firstName : string, lastname : string, role : string, address : string, dateOfBirth : Date, gender : string, city : string, phoneNumber : string) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.gender = gender;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

}
