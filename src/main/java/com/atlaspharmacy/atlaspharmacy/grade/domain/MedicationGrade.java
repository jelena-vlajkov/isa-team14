package com.atlaspharmacy.atlaspharmacy.grade.domain;

import com.atlaspharmacy.atlaspharmacy.grade.domain.enums.GradeType;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;

@Entity
@Table(name = "medication_grade")
@DiscriminatorValue(GradeType.Values.MedicationGrade)
public class MedicationGrade extends Grade{

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Medication medication;

    public MedicationGrade(int grade, Patient patient, Medication medication) {
        super(grade, patient);
        this.medication = medication;
    }

    public MedicationGrade() {}

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}
