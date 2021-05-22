import { City } from "./city";
import { Coordinates } from "./coordinates";
import { State } from "./state";

export class Address{
    public id: Number;
    public coordinates : Coordinates;
    public state : State;
    public city : City;
    public street : String;


    constructor(id:Number,street:String,city:City, coords : Coordinates, state : State){
        this.id = id;
        this.street = street;
        this.city = city;
        this.coordinates = coords;
        this.state = state;
    }
}
