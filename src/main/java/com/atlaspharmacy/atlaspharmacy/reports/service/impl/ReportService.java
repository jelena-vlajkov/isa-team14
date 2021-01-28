package com.atlaspharmacy.atlaspharmacy.reports.service.impl;

import com.atlaspharmacy.atlaspharmacy.reports.DTO.SaveReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.domain.Report;
import com.atlaspharmacy.atlaspharmacy.reports.repository.ReportRepository;
import com.atlaspharmacy.atlaspharmacy.reports.service.IReportService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveReport(SaveReportDTO reportDTO) {
        reportRepository.save(new Report(reportDTO.getDate(), null, (Patient) userRepository.findById(reportDTO.getPatientId()).get(),
                (Dermatologist) userRepository.findById(reportDTO.getDermatologistId()).get(), reportDTO.getReportNotes()));
    }
}
