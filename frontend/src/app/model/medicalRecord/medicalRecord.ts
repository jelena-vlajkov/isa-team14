import { Allergy } from "../medications/allergy";
import { Ingredient } from "../medications/ingredient";


export class MedicalRecord {
    public id : String;
    public patientName : String;
    public ingredients : Ingredient[] = new Array();
    public allergies : Allergy[] = new Array();
   

    constructor(patientName : string,  ingredients: Ingredient[], allergies : Allergy[]) {
        this.patientName = patientName;
        this.ingredients = ingredients;
         this.allergies = allergies;
    }

}
