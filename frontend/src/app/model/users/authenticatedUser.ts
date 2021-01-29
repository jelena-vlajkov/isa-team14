import { Role } from './role';
export class AuthenticatedUser {
    public id : string;
    public role : Role;
    public username : string;
    public token : string;

    constructor(id : string, role : Role, username : string, token : string) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.token = token;
    }
}