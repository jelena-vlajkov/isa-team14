package com.atlaspharmacy.atlaspharmacy.reservations.DTO;

public class IssueReservationDTO {
    private Long medicalStaffId;
    private int uniqueIdentifier;

    public IssueReservationDTO(Long medicalStaffId, int uniqueIdentifier) {
        this.medicalStaffId = medicalStaffId;
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Long getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(Long medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(int uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }
}
