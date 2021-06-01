export class SearchParam {
    public name : String;
    private date : Date;
    private medicalStaffId : Number
    public sortingType : String; 
    constructor(private name1 : string, private date1 : Date, private medicalStaffId1 : Number) {
        this.date = date1;
        this.name = name1;
        this.medicalStaffId = medicalStaffId1;
    }
}