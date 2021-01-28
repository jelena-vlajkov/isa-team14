package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reports")
@Proxy(lazy = false)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "medication_ingredients",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medication> medication;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dermatologist dermatologist;

    public Report() {
    }

    public Report(Long id, Date date, List<Medication> medication, Patient patient, Dermatologist dermatologist) {
        this.id = id;
        this.date = date;
        this.medication = medication;
        this.patient = patient;
        this.dermatologist = dermatologist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}
