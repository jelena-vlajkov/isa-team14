package com.atlaspharmacy.atlaspharmacy.reports.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = ReportType.Values.Counseling)
public class CounselingReport extends Report {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Pharmacist pharmacist;

    public CounselingReport() {
        super();
    }

    public CounselingReport(Date date,  Patient patient, Pharmacy pharmacy, String reportType, String reportNotes, Pharmacist pharmacist) {
        super(date, patient, pharmacy, reportType, reportNotes);
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
