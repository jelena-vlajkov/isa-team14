import { Medication } from "@app/model/medications/medication";

export class MedicationInStorage{
    public medication : Medication;
    public quantity : Number;

    constructor(medication : Medication, quantity : Number){
        this.medication = medication;
        this.quantity = quantity;
    }
}