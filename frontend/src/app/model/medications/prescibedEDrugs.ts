export class PrescribedEdrugs{
    public quantity : Number;
    public prescribedMedicationName : String

    constructor(prescribedMedicationName: String, quantity : Number){
        this.prescribedMedicationName = prescribedMedicationName;
        this.quantity = quantity;
    }
}