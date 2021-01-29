package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

public class PenaltyDTO {
    private Long patientId;
    private Long appointmentId;

    public PenaltyDTO() {
    }

    public PenaltyDTO(Long patientId, Long appointmentId) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
