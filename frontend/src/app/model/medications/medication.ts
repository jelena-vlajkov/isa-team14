import { DrugForm } from "./drugForm";
import { DrugKind } from "./drugKind";
import { DrugType } from "./drugType";
import { Ingredient } from "./ingredient";
import { TypeOfPrescribing } from "./typeOfPrescribing";

export class Medication {
    public id:Number;
    public name : string;
    public drugForm : DrugForm;
    public drugType : DrugType;
    public producer : string;
    public typeOfPrescribing : TypeOfPrescribing;
    public additionalNotes : string;
    public contraindications : string;
    public dailyDose : number;
    public drugKind : DrugKind;
    public code: Number;
    public grade : Number;
    public dosage : Number;
    public ingredients : Ingredient[] = new Array();
    public substituteMedication :  Medication[] = new Array();

    constructor(id:Number,name : string,  drugForm : DrugForm, drugType : DrugType,
        producer:string, typeOfPrescribing : TypeOfPrescribing, contraindications: string,
        additionalNotes: string, dailyDose: number, drugKind:DrugKind, subMeds : Medication[], code: Number, ingredients: Ingredient[], grade : Number, dosage : Number) {
        this.id=id;
        this.name = name;
        this.drugForm = drugForm;
        this.drugKind = drugKind;
        this.drugType = drugType;
        this.producer = producer;
        this.typeOfPrescribing = typeOfPrescribing;
        this.contraindications = contraindications;
        this.additionalNotes = additionalNotes;
        this.dailyDose = dailyDose;
        this.substituteMedication = subMeds;
        this.code = code;
        this.ingredients = ingredients;
        this.grade = grade;
        this.dosage = dosage;
    }

}
