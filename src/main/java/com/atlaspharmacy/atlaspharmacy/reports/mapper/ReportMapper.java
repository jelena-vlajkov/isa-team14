package com.atlaspharmacy.atlaspharmacy.reports.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.ReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.SaveReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.domain.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportMapper {
    public static ReportDTO mapReportToDto(Report report) {
        List<String> medications = new ArrayList<>();
        for (Medication medication : report.getMedication()) {
            medications.add(medication.getName());
        }

        return new ReportDTO(report.getDate(), medications,
                report.getPatient().getName() + " " + report.getPatient().getSurname(),
                report.getPatient().getEmail(),
                report.getReportNotes());
    }

    public static List<ReportDTO> mapReportsToDtos(List<Report> reports) {
        List<ReportDTO> reportDTOS = new ArrayList<>();
        for(Report report : reports) {
            reportDTOS.add(mapReportToDto(report));
        }
        return reportDTOS;
    }



}
