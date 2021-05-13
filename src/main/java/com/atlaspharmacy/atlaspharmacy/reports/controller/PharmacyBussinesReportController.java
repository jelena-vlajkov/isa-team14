package com.atlaspharmacy.atlaspharmacy.reports.controller;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugConsumptionDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PharmacyIncomeReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PharmacyPeriodReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.service.IPharmacyBussinesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pharmacyBussinesReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyBussinesReportController {
    private final IPharmacyBussinesReportService pharmacyBussinesReportService;

    @Autowired
    public PharmacyBussinesReportController(IPharmacyBussinesReportService drugsConsumptionReportService) {
        this.pharmacyBussinesReportService = drugsConsumptionReportService;
    }

    @GetMapping(value = "/getDrugConsumptionReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DrugConsumptionDTO> getDrugsConsumptionsForPharmacyAndPeriod(
            @RequestBody PharmacyPeriodReportDTO pharmacyPeriodReportDTO) {
        return pharmacyBussinesReportService.getDrugsConsumptionReportForPharmacyAndPeriod(pharmacyPeriodReportDTO);
    }

    @GetMapping(value = "/getPharmacyIncomeReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PharmacyIncomeReportDTO getPharmacyIncomeReport(
            @RequestBody PharmacyPeriodReportDTO pharmacyPeriodReportDTO) {
        return pharmacyBussinesReportService.getPharmacyIncomeReportForPeriod(pharmacyPeriodReportDTO);
    }
}
