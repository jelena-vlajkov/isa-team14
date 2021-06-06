package com.atlaspharmacy.atlaspharmacy.reports.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.PharmacyAdminAuthorization;
import com.atlaspharmacy.atlaspharmacy.customannotations.ReportAuthorization;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugInquiryReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.SaveReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.service.IDrugInquiryService;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(allowedHeaders = "*",origins = "*")
@Controller
@RequestMapping(value = "/drugInquiryReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugInquiryReportController {
    private final IDrugInquiryService drugInquiryService;

    @Autowired
    public DrugInquiryReportController(IDrugInquiryService drugInquiryService) {
        this.drugInquiryService = drugInquiryService;
    }

    @PostMapping(value = "addInquiryReport", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ReportAuthorization
    public @ResponseBody
    ResponseEntity<?> addInquiryReport(@RequestBody DrugInquiryReportDTO reportDTO) throws ParseException {
        try {
            drugInquiryService.addDrugInquiry(reportDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PharmacyAdminAuthorization
    public @ResponseBody
    List<DrugInquiryReportDTO> getAll() throws ParseException {
        return drugInquiryService.getAll();
    }


}
