import { Medication } from "./medication";

export class OrderedMedication{
    public medication : Medication;
    public quantity : Number;

    constructor(medication: Medication, quantity : Number){
        this.medication = medication;
        this.quantity = quantity;
    }
}