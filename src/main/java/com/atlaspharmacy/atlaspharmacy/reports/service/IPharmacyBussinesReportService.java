package com.atlaspharmacy.atlaspharmacy.reports.service;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.*;

import java.util.List;

public interface IPharmacyBussinesReportService {
    List<DrugConsumptionDTO> getDrugsConsumptionReportForPharmacyAndPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO);
    PharmacyIncomeReportDTO getPharmacyIncomeReportForPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO);
}
