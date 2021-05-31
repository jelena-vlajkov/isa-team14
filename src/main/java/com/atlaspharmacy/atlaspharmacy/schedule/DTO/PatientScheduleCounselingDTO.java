package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import java.util.Date;

public class PatientScheduleCounselingDTO {
    private Long pharmacistId;
    private String startTimeRange;
    private String endTimeRange;
    private Long patientId;
    private String type;
    private Long pharmacyId;

    public PatientScheduleCounselingDTO(Long pharmacistId, String startTimeRange, String endTimeRange, Long patientId, String type, Long pharmacyId) {
        this.pharmacistId = pharmacistId;
        this.startTimeRange = startTimeRange;
        this.endTimeRange = endTimeRange;
        this.patientId = patientId;
        this.type = type;
        this.pharmacyId = pharmacyId;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getStartTimeRange() {
        return startTimeRange;
    }

    public void setStartTimeRange(String startTimeRange) {
        this.startTimeRange = startTimeRange;
    }

    public String getEndTimeRange() {
        return endTimeRange;
    }

    public void setEndTimeRange(String endTimeRange) {
        this.endTimeRange = endTimeRange;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
