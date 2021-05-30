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
    Long getDrugsConsumptionsForMonth(
            @RequestParam("month") int month,@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsForMonth(month,year,pharmacyId);
    }


    @GetMapping(value = "/getDrugConsumptionsForHalfYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    Long getDrugsConsumptionsForHalfYear(
            @RequestParam("part") int part,@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsForHalfYear(part,year,pharmacyId);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getDrugConsumptionsForYear")
    public @ResponseBody
    Long getDrugsConsumptionsForYear(@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getDrugConsumptionsForYear(year,pharmacyId);
    }

    @GetMapping(value = "/getPharmacyIncomeForMonth")
    @PharmacyAdminAuthorization
    public @ResponseBody
    Long getPharmacyIncomeForMonth(
            @RequestParam("month") int month,@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeForMonth(month,year,pharmacyId);
    }


    @GetMapping(value = "/getPharmacyIncomeForHalfYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    Long getPharmacyIncomeForHalfYear(@RequestParam("part") int part,@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeForHalfYear(part,year,pharmacyId);
    }



    @GetMapping(value = "/getPharmacyIncomeForYear")
    @PharmacyAdminAuthorization
    public @ResponseBody
    Long getPharmacyIncomeForYear(@RequestParam("year")int year,@RequestParam("pharmacyId") Long pharmacyId) {
        return pharmacyBussinesReportService.getPharmacyIncomeForYear(year,pharmacyId);
    }




}
