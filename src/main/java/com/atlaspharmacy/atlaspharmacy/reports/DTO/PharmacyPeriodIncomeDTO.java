package com.atlaspharmacy.atlaspharmacy.reports.DTO;

public class PharmacyPeriodIncomeDTO {
    private Long pharmacyId;
    private PeriodDTO period;

    public PharmacyPeriodIncomeDTO() {}

    public PharmacyPeriodIncomeDTO(Long pharmacyId, PeriodDTO period) {
        this.pharmacyId = pharmacyId;
        this.period = period;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public PeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDTO period) {
        this.period = period;
    }
}
