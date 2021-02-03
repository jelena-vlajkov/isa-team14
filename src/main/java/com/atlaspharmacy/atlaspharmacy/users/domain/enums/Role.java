package com.atlaspharmacy.atlaspharmacy.users.domain.enums;

public enum Role {
    SysAdmin(Values.SysAdmin), PharmacyAdmin(Values.PharmacyAdmin), Patient(Values.Patient), Pharmacist(Values.Pharmacist), Dermatologist(Values.Dermatologist), MedicalStaff(Values.MedicalStaff), Supplier(Values.Supplier);

    Role(String value) {
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of Role!");
    }

    public static class Values {
        public static final String Pharmacist = "Pharmacist";
        public static final String Dermatologist = "Dermatologist";
        public static final String Patient = "Patient";
        public static final String SysAdmin = "SysAdmin";
        public static final String PharmacyAdmin = "PharmacyAdmin";
        public static final String MedicalStaff = "MedicalStaff";
        public static final String Supplier = "Supplier";
    }
}
