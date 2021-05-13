package com.atlaspharmacy.atlaspharmacy.reports.DTO;

public class PharmacyIncomeReportDTO {
    private Long pharmacyId;
    private double totalIncome;

    public PharmacyIncomeReportDTO() {}

    public PharmacyIncomeReportDTO(Long pharmacyId, double totalIncome) {
        this.pharmacyId = pharmacyId;
        this.totalIncome = totalIncome;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
