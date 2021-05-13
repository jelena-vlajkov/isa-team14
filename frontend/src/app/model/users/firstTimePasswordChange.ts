export class FirstTimePasswordChange{
    public email : string;
    public repnewpassword : String;
    public newpassword : String;
    public passwordChanged : boolean;
    constructor(email:string, repnewpassword : String, newpassword:String, passwordChanged : boolean){
        this.email = email;
        this.repnewpassword = repnewpassword;
        this.newpassword = newpassword;
        this.passwordChanged = passwordChanged;
    }
}