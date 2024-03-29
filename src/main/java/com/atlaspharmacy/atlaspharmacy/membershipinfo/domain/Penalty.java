package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Proxy(lazy = false)
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Patient patient;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Appointment appointment;

    private Date givenDate;

    public Penalty() {
    }

    public Penalty(Patient patient, Appointment appointment) {
        this.patient = patient;
        this.appointment = appointment;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient user) {
        this.patient = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public boolean isPatient(Long id) {
        return getAppointment().getId().equals(id);
    }
}
