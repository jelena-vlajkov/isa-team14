import { Address } from "@app/model/address/address";
import { Gender } from "@app/model/users/patient/gender";
import { Role } from "@app/model/users/role";

export class SystemAdmin{
    public sysName : String;
    public sysSurname : String;
    public sysDateOfBirth : Date;
    public sysPhoneNumber : String;
    public sysEmail : String;
    public sysPassword : String;
    public sysGender : Gender;
    public sysAddress : Address;
    public sysRole : Role;
    public sysAuthorities : Number[];
    public firstTimeChanged : boolean;

    constructor(name:String, surname : String, dateOfBirth: Date, phoneNumber : String, email : String, password : String, gender: Gender, address : Address, role : Role, autorities : Number[], firstTimeChanged : boolean){
        this.sysName = name;
        this.sysSurname = surname;
        this.sysDateOfBirth = dateOfBirth;
        this.sysPhoneNumber = phoneNumber;
        this.sysEmail = email;
        this.sysPassword = password;
        this.sysGender = gender;
        this.sysAddress = address;
        this.sysRole = role;
        this.sysAuthorities = autorities;
        this.firstTimeChanged = firstTimeChanged;
    }


}
