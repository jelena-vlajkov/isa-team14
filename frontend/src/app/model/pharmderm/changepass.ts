export class EmployeePasswordChanger{
    public email : string;
    public oldpassword : String;
    public newpassword : String;
    public passwordChanged : boolean;
    constructor(email:string, oldpassword : String, newpassword:String, passwordChanged : boolean){
        this.email = email;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.passwordChanged = passwordChanged;
    }
}