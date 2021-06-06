package com.atlaspharmacy.atlaspharmacy.reports.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.SaveReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.domain.CounselingReport;
import com.atlaspharmacy.atlaspharmacy.reports.domain.ExaminationReport;
import com.atlaspharmacy.atlaspharmacy.reports.domain.enums.ReportType;
import com.atlaspharmacy.atlaspharmacy.reports.repository.ReportRepository;
import com.atlaspharmacy.atlaspharmacy.reports.service.IReportService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportService implements IReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, PharmacyRepository pharmacyRepository, AppointmentRepository appointmentRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void saveReport(SaveReportDTO reportDTO) throws Exception {
        if (!userRepository.findById(reportDTO.getMedicalStaffId()).isPresent()) {
            throw new Exception("Invalid request!");
        }
        User user = userRepository.findById(reportDTO.getMedicalStaffId()).get();
        if (!appointmentRepository.findById(reportDTO.getAppointmentId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        Appointment a = appointmentRepository.findById(reportDTO.getAppointmentId()).get();
        a.setFinished(true);
        appointmentRepository.save(a);
        if(user.getRole().equals(Role.Values.Dermatologist))
            reportRepository.save(new ExaminationReport(new Date(), (Patient) userRepository.findById(reportDTO.getPatientId()).get(),
                     pharmacyRepository.findById(reportDTO.getPharmacyId()).get()
                     ,ReportType.Values.Examination, reportDTO.getReportNotes(), (Dermatologist) userRepository.findById(reportDTO.getMedicalStaffId()).get()));
        else
            reportRepository.save(new CounselingReport(new Date(), (Patient) userRepository.findById(reportDTO.getPatientId()).get(),
                    pharmacyRepository.findById(reportDTO.getPharmacyId()).get(),
                    ReportType.Values.Counseling, reportDTO.getReportNotes(), (Pharmacist) userRepository.findById(reportDTO.getMedicalStaffId()).get()));

    }
}
