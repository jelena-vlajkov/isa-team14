export class EPrescription {
    public id : Number;
    public date : Date;
    public patientName : String;
    public pharmacyName : String;
    public type : String;

    public constructor(id : Number, date : Date, patientName : String, pharmacyName : String) {
        this.id = id;
        this.date = date;
        this.patientName = patientName;
        this.pharmacyName = pharmacyName;
    }
}