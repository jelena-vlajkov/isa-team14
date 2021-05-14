import { MedicationInStorage } from "./medicationInStorage";
import { Supplier } from "./supplier";

export class SupplierStorage{
    public supplier : Supplier;
    public medicationInStorage : MedicationInStorage[];

    constructor(supplier : Supplier, medicationInStorage : MedicationInStorage[]){
        this.supplier = supplier;
        this.medicationInStorage = medicationInStorage;
    }

}