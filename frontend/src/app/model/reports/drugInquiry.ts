import {Medication} from "@app/model/medications/medication";

export class DrugInquiry{
  public date:Date;
  public medication:Medication;

  constructor(date:Date,medication:Medication) {
    this.date=date;
    this.medication=medication;
  }
}
