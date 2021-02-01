package com.atlaspharmacy.atlaspharmacy.reports.controller;

import com.atlaspharmacy.atlaspharmacy.customannotations.ReportAuthorization;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.SaveReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.exceptions.UnableToSaveReportException;
import com.atlaspharmacy.atlaspharmacy.reports.service.IReportService;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
    private final IReportService reportService;

    private static final String ERROR = "An internal error occurred while saving your report.";
    private static final String NO_USER_EXCEPTION = "An internal error occurred while saving your report. User id could not be found.";


    @Autowired
    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "saveReport", produces = MediaType.APPLICATION_JSON_VALUE)
    @ReportAuthorization
    public @ResponseBody
    ResponseEntity<?> saveReport(@RequestBody SaveReportDTO reportDTO) throws ParseException {
        reportService.saveReport(reportDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(DueDateSoonException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Exception handleException(Exception e) {
        return new UnableToSaveReportException(ERROR);
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    UnableToSaveReportException handleException(NoSuchElementException e) {
        return new UnableToSaveReportException(NO_USER_EXCEPTION);
    }

}
