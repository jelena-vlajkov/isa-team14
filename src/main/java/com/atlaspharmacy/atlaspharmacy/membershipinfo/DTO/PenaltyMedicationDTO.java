package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

public class PenaltyMedicationDTO {
    private Long patientId;
    private Long drugReservationId;

    public PenaltyMedicationDTO(Long patientId, Long drugReservationId) {
        this.patientId = patientId;
        this.drugReservationId = drugReservationId;
    }

    public PenaltyMedicationDTO() {}

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDrugReservationId() {
        return drugReservationId;
    }

    public void setDrugReservationId(Long drugReservationId) {
        this.drugReservationId = drugReservationId;
    }
}
