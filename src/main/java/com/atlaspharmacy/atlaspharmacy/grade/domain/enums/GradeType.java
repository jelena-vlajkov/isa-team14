package com.atlaspharmacy.atlaspharmacy.grade.domain.enums;

public enum GradeType {
    PharmacistGrade(GradeType.Values.PharmacistGrade), PharmacyGrade(GradeType.Values.PharmacyGrade),
    MedicationGrade(GradeType.Values.MedicationGrade), DermatologistGrade(GradeType.Values.DermatologistGrade);

    GradeType(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of grade type!");
    }

    public static class Values {
        public static final String PharmacistGrade = "PharmacistGrade";
        public static final String PharmacyGrade = "PharmacyGrade";
        public static final String MedicationGrade = "MedicationGrade";
        public static final String DermatologistGrade = "DermatologistGrade";
    }
}
