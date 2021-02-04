export class PasswordChanger{
    public user_id : Number;
    public oldpassword : String;
    public newpassword : String;
    constructor(user_id:Number, oldpassword : String, newpassword:String){
        this.user_id = user_id;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }
}