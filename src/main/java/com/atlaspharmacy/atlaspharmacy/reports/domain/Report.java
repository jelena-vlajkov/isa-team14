package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;
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
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType=DiscriminatorType.STRING)
@Proxy(lazy = false)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "reports_medication",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medication> medication;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacy pharmacy;
    private String reportType;
    private String reportNotes;

    public Report() {
    }

    public Report(Date date, List<Medication> medication, Patient patient, Pharmacy pharmacy, String reportType, String reportNotes) {
        this.date = date;
        this.medication = medication;
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.reportType = reportType;
        this.reportNotes = reportNotes;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
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

    public String getReportNotes() {
        return reportNotes;
    }

    public void setReportNotes(String reportNotes) {
        this.reportNotes = reportNotes;
    }

    public boolean isExaminationReport() {
        return reportType.equals(ReportType.Values.Examination);
    }
}
