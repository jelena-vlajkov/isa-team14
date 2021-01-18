package com.atlaspharmacy.atlaspharmacy.appointment;
import java.util.Date;

public class Period {
    private Date start;
    private Date end;

    public Period(){
    }
    public Period(Date start, Date end){
        this.start = start;
        this.end = end;
    }
    public Period(Period p){
        this.start = p.start;
        this.end = p.end;
    }

    public Date getStart(){
        return this.start;
    }

    public Date getEnd(){
        return this.end;
    }

    public void setStart(Date start){
        this.start=start;
    }

    public void setEnd(Date end){
        this.end=end;
    }
}
