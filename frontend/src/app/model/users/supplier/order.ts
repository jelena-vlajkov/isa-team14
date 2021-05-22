import { OrderedMedication } from "@app/model/medications/orderedMedication";
import { Pharmacy } from "@app/model/pharmacy/pharmacy";

export class Order{
    public id: Number;
    public dueDate : Date;
    public orderedMedications : OrderedMedication[];
    public pharmacy : Pharmacy;
    public uniqueidentifier : number;

    constructor(id:Number,dueDate : Date, orderedMedication : OrderedMedication[], pharmacy :Pharmacy, uniqueidentifier: number){
        this.id = id;
        this.dueDate = dueDate;
        this.orderedMedications = orderedMedication;
        this.pharmacy = pharmacy;
        this.uniqueidentifier = uniqueidentifier;
    }
}
