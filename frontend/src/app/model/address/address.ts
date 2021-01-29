import { City } from "./city";
import { Coordinates } from "./coordinates";

export class Address{
    public street : String;
    public city : City;
    public coordinates : Coordinates
    constructor(street:String,city:City, coords : Coordinates){
        this.street = street;
        this.city = city;
        this.coordinates = coords;
    }
}