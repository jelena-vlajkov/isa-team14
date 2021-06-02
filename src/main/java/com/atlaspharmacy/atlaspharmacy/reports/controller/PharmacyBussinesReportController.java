package com.atlaspharmacy.atlaspharmacy.reports.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/pharmacyBussinesReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyBussinesReportController {
    private final IPharmacyBussinesReportService pharmacyBussinesReportService;

    @Autowired
    public PharmacyBussinesReportController(IPharmacyBussinesReportService drugsConsumptionReportService) {
        this.pharmacyBussinesReportService = drugsConsumptionReportService;
    }



    @GetMapping(value = "/getDrugConsumptionsForMonth")
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<Long> getDrugsConsumptionsForMonth(@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsReportsByMonths(year,pharmacyId);
    }


    @GetMapping(value = "/getDrugConsumptionsForHalfYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<Long> getDrugsConsumptionsForHalfYear(@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsReportsByHalfYears(year,pharmacyId);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getDrugConsumptionsForYear")
    public @ResponseBody
    List<Long> getDrugsConsumptionsForYear(@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsReportsByYears(startYear,endYear,pharmacyId);
    }

    @GetMapping(value = "/getPharmacyIncomeForMonth")
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<Long> getPharmacyIncomeForMonth(@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeReportByMonths(year,pharmacyId);
    }


    @GetMapping(value = "/getPharmacyIncomeForHalfYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<Long> getPharmacyIncomeForHalfYear(@RequestParam("year") int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeReportByHalfYears(year,pharmacyId);
    }



    @GetMapping(value = "/getPharmacyIncomeForYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<Long> getPharmacyIncomeForYear(@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeReportByYears(startYear,endYear,pharmacyId);
    }




}
