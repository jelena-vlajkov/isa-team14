import { Medication } from "@app/model/medications/medication";
import { Supplier } from "./supplier";

export class NewMedicationToStorage{
    public medication : Medication;
    public quantity : Number;
    public supplier : Supplier;
    
    constructor(medication : Medication, quantity : Number, supplier : Supplier){
        this.medication = medication;
        this.quantity = quantity;
        this.supplier = supplier;
    }
    
}