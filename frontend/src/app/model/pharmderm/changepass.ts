export class EmployeePasswordChanger{
    public email : String;
    public oldpassword : String;
    public newpassword : String;
    public passwordChanged : boolean;
    constructor(email:String, oldpassword : String, newpassword:String, passwordChanged : boolean){
        this.email = email;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.passwordChanged = passwordChanged;
    }
}
