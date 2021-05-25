package com.atlaspharmacy.atlaspharmacy.schedule.domain;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = AppointmentType.Values.Examination)
public class Examination extends Appointment{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dermatologist dermatologist;

    public Examination() {
    }

    public Examination(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Examination(Period appointmentPeriod, double cost, String type, boolean isCanceled, Dermatologist dermatologist, Patient patient) {
        super(appointmentPeriod, cost, type, isCanceled, patient);
        this.dermatologist = dermatologist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}
