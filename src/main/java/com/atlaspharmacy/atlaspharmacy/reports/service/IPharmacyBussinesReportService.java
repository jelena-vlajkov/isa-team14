package com.atlaspharmacy.atlaspharmacy.reports.service;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.*;

import java.util.Date;
import java.util.List;

public interface IPharmacyBussinesReportService {
    List<DrugConsumptionDTO> getDrugsConsumptionReportForPharmacyAndPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO);
    Long getPharmacyIncomeReportForPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO);
    Long getDrugConsumptionsForMonth(int month, int year,Long pharmacyId);
    Long getDrugConsumptionsForHalfYear(int part, int year,Long pharmacyId);
    Long  getDrugConsumptionsForYear(int year,Long pharmacyId);
    Long getPharmacyIncomeForMonth(int month, int year,Long pharmacyId);
    Long getPharmacyIncomeForHalfYear(int part, int year,Long pharmacyId);
    Long  getPharmacyIncomeForYear(int year,Long pharmacyId);
    List<Long> getPharmacyIncomeReportByMonths(int year,Long pharmacyId);
    List<Long> getPharmacyIncomeReportByHalfYears(int year,Long pharmacyId);
    List<Long> getPharmacyIncomeReportByYears(int startYear,int endYear,Long pharmacyId);
    List<Long> getDrugConsumptionsReportsByMonths(int year,Long pharmacyId);
    List<Long> getDrugConsumptionsReportsByHalfYears(int year,Long pharmacyId);
    List<Long> getDrugConsumptionsReportsByYears(int startYear,int endYear,Long pharmacyId);

}
