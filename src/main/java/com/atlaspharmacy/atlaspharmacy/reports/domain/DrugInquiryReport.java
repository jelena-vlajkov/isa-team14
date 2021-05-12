package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "druginquiries")
public class DrugInquiryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @OneToOne
    private Medication medication;

    public DrugInquiryReport() {}

    public DrugInquiryReport(Long id, Date date, Medication medication) {
        this.id = id;
        this.date = date;
        this.medication = medication;
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

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}
