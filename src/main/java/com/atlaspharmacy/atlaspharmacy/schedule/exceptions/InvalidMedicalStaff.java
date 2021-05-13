package com.atlaspharmacy.atlaspharmacy.schedule.exceptions;

public class InvalidMedicalStaff extends Throwable {
    private static final String INVALID_MEDICAL_STAFF = "Invalid medical staff id";
    public InvalidMedicalStaff() {
        super(INVALID_MEDICAL_STAFF);
    }

    public InvalidMedicalStaff(String message) {
        super(message);
    }
}
