import { Medication } from '@app/model/medications/medication';
import { Dermatologist } from '../dermatologist/dermatologist';
import { Pharmacy } from '@app/model/pharmacy/pharmacy';
import { Pharmacist } from '../pharmacist/pharmacist';

export class UpdateGrade {
    public Id : Number;
    public grade : Number;
    public gradeType : String;
    public patientId : Number;
    public medicationId : Number;
    public pharmacyId : Number;
    public pharmacistId : Number;
    public dermatologistId : Number;
    public medicationName : String;
    public dermatologistName : String;
    public pharmacyName : String;
    public pharmacistName : String;


    constructor(){}
}
