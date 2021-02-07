import { OrderedMedication } from "@app/model/medications/orderedMedication";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";

export class Order{
    public dueDate : Date;
    public orderedMedication : OrderedMedication[];
    public pharmacy : Pharmacy;
    public editableDue : Date;
    public uniqueidentifier : number;

    constructor(dueDate : Date, orderedMedication : OrderedMedication[], pharmacy :Pharmacy, editableDue : Date, uniqueidentifier: number){
        this.dueDate=dueDate;
        this.orderedMedication = orderedMedication;
        this.pharmacy = pharmacy;
        this.editableDue = editableDue;
        this.uniqueidentifier = uniqueidentifier;
    }
}