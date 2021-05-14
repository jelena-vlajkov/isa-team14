import { Allergy } from "./allergy";

export class Ingredient {
    public name : String;
    public allergies : Allergy[];

    constructor(name: String, allergies : Allergy[]){
        this.name = name;
        this.allergies = allergies;
    }
}