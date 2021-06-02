import {VacationRequest} from "@app/model/pharmderm/vacationrequest";

export class VacationRequestAnswer{
  public vacationRequest : VacationRequest;
  public accepted : boolean;
  public explanation : String;

  constructor(vacationRequest: VacationRequest,accepted : boolean, explanation : String) {
    this.vacationRequest = vacationRequest;
    this.accepted = accepted;
    this.explanation = explanation;
  }

}
