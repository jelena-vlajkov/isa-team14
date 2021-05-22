package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = ReportType.Values.Examination)
public class ExaminationReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Dermatologist dermatologist;

    public ExaminationReport() {
        super();
    }

    public ExaminationReport(Date date, List<Medication> medication, Patient patient, Pharmacy pharmacy, String reportType, String reportNotes, Dermatologist dermatologist) {
        super(date, medication, patient, pharmacy, reportType, reportNotes);
        this.dermatologist = dermatologist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
}
