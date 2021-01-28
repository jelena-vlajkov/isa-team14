package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = ReportType.Values.Counseling)
public class CounselingReport extends Report {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pharmacist pharmacist;

    public CounselingReport() {
        super();
    }

    public CounselingReport(Date date, List<Medication> medication, Patient patient, String reportNotes, String reportType, Pharmacist pharmacist) {
        super(date, medication, patient, reportNotes, reportType);
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
