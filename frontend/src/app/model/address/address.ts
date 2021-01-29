import { City } from "./city";
import { Coordinates } from "./coordinates";
import { State } from "./state";

export class Address{
    public street : String;
    public city : City;
    public coordinates : Coordinates
    public state : State;
    constructor(street:String,city:City, coords : Coordinates, state : State){
        this.street = street;
        this.city = city;
        this.coordinates = coords;
        this.state = state;
    }
}