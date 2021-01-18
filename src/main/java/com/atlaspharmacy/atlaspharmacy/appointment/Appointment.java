package com.atlaspharmacy.atlaspharmacy.appointment;

public class Appointment {
    private Period period;

    public Appointment(){

    }
    public Appointment(Period period){
        this.period = period;
    }
    public Appointment(Appointment appointment){
        this.period = appointment.period;
    }

    public void setPeriod(Period period){
        this.period = period;
    }
    public Period getPeriod(){
        return period;
    }
}
