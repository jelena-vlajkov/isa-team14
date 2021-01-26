package com.atlaspharmacy.atlaspharmacy.schedule.domain;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = AppointmentType.Values.Examination)
public class Examination extends Appointment{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dermatologist dermatologist;

    public Examination() {
    }

    public Examination(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Examination(Period appointmentPeriod, double cost, AppointmentType type, boolean isCanceled, Dermatologist dermatologist) {
        super(appointmentPeriod, cost, type, isCanceled);
        this.dermatologist = dermatologist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}
