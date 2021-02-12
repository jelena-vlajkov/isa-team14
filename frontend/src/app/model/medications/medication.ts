import { DrugForm } from "./drugForm";
import { DrugKind } from "./drugKind";
import { DrugType } from "./drugType";
import { Ingredient } from "./ingredient";
import { TypeOfPrescribing } from "./typeOfPrescribing";

export class Medication {
    public name : string;
    public drugForm : DrugForm;
    public drugType : DrugType;
    public producer : string;
    public typeOfPrescribing : TypeOfPrescribing;
    public contraindications : string;
    public additionalNotes : string;
    public dailyDose : number;
    public drugKind : DrugKind;
    public substituteMedication :  Medication[] = new Array();
    public code: Number;
    public ingredients : Ingredient[] = new Array();
    public grade : Number;
    public dosage : Number;

    constructor(name : string,  drugForm : DrugForm, drugType : DrugType, 
        producer:string, typeOfPrescribing : TypeOfPrescribing, contraindications: string,
        additionalNotes: string, dailyDose: number, drugKind:DrugKind, subMeds : Medication[], code: Number, ingredients: Ingredient[], grade : Number, dosage : Number) {
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