package com.atlaspharmacy.atlaspharmacy.schedule.domain;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType=DiscriminatorType.STRING)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "startTime", column = @Column(name = "appointmentStartTime")),
            @AttributeOverride( name = "endTime", column = @Column(name = "appointmentEndTime"))
    })
    private Period appointmentPeriod;
    private double cost;
    @Column(insertable = false, updatable = false)
    private AppointmentType type;
    private boolean isCanceled;

    public Appointment() {
    }

    public Appointment(Period appointmentPeriod, double cost, AppointmentType type, boolean isCanceled) {
        this.appointmentPeriod = appointmentPeriod;
        this.cost = cost;
        this.type = type;
        this.isCanceled = isCanceled;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Period getAppointmentPeriod() {
        return appointmentPeriod;
    }

    public void setAppointmentPeriod(Period appointmentPeriod) {
        this.appointmentPeriod = appointmentPeriod;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
