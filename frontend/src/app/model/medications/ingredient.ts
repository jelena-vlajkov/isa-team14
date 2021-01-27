export class Ingredient {
    public name : String;
    public allergies : Number[];

    constructor(name: String, allergies : Number[]){
        this.name = name;
        this.allergies = allergies;
    }
}