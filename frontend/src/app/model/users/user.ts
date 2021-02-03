import { Role } from "@app/model/users/role";

export class User {
    public id: number;
    public name: string;
    public password: string;
    public username: string;
    public surname: string;
    public role:Role;
    public token?: string;

  constructor(id : number, name:string,password:string,username : string,
              surname : string, token : string,role:Role) {
    this.id = id;
    this.role = role;
    this.name = name;
    this.password=password;
    this.username=username;
    this.surname=surname;
    this.token = token;
  }
}

