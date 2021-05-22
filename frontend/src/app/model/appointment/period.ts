export class Period{
    public startTime : Date;
    public endTime : Date;

    constructor(startPeriod : Date, endPeriod : Date){
        this.startTime=startPeriod;
        this.endTime = endPeriod;
    }
}
