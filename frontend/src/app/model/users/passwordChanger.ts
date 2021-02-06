export class PasswordChanger{
    public user_id : Number;
    public oldpassword : String;
    public newpassword : String;
    public passwordChanged : boolean;
    constructor(user_id:Number, oldpassword : String, newpassword:String, passwordChanged : boolean){
        this.user_id = user_id;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.passwordChanged = passwordChanged;
    }
}