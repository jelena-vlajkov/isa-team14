import { State } from "./state";

export class City{
    public name : String;
    public state : State;
    constructor(name:String, state:State){
        this.name = name;
        this.state = state;
    }
}