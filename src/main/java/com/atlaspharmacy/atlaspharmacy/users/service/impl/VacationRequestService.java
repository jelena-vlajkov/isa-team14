package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.*;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.VacationRequestStatus;
import com.atlaspharmacy.atlaspharmacy.users.mapper.VacationRequestMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class VacationRequestService implements IVacationRequestService {
    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;
    private final IPharmacistService pharmacistService;
    private final IDermatologistService dermatologistService;
    private final IWorkDayService workDayService;
    private final AppointmentRepository appointmentRepository;
    private final IEmailService emailService;

    @Autowired
    VacationRequestService(VacationRequestRepository vacationRequestRepository, UserRepository userRepository, IPharmacistService pharmacistService, IDermatologistService dermatologistService, IWorkDayService workDayService, AppointmentRepository appointmentRepository, IEmailService emailService) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.userRepository = userRepository;
        this.pharmacistService = pharmacistService;
        this.dermatologistService = dermatologistService;
        this.workDayService = workDayService;
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
    }

    @Override
    public void saveVacationRequest(VacationRequestDTO dto) throws Exception {
        if (!userRepository.findById(dto.getMedicalStaffId()).isPresent()) {
            throw new Exception("Invalid request!");
        }

        List<WorkDay> workDays = workDayService.getAllWorkDaysInPeriod(dto.getMedicalStaffId(), dto.getStartDate(), dto.getEndDate());
        if (workDays.size() == 0) {
            saveRequestsToAllPharmacies(dto);
        } else {
            for (WorkDay w : workDays) {
                VacationRequest vacationRequest = VacationRequestMapper.mapToRequest(dto);
                vacationRequest.setMedicalStaff((MedicalStaff) userRepository.findById(dto.getMedicalStaffId()).get());
                vacationRequest.setPharmacy(w.getPharmacy());
                vacationRequest.setStatus(VacationRequestStatus.PENDING);
                vacationRequestRepository.save(vacationRequest);
                workDayService.updateWorkDay(w.getId());
            }
        }
    }

    private void saveRequestsToAllPharmacies(VacationRequestDTO dto) throws ParseException {
        User user = userRepository.findById(dto.getMedicalStaffId()).get();
        if (user.getRole().equals(Role.Values.Pharmacist)) {
            Pharmacist pharmacist = (Pharmacist) user;
            VacationRequest vacationRequest = VacationRequestMapper.mapToRequest(dto);
            vacationRequest.setMedicalStaff((MedicalStaff) userRepository.findById(dto.getMedicalStaffId()).get());
            vacationRequest.setPharmacy(pharmacist.getPharmacy());
            vacationRequest.setStatus(VacationRequestStatus.PENDING);
            vacationRequestRepository.save(vacationRequest);
        } else {
            Dermatologist dermatologist = (Dermatologist) user;
            for (Pharmacy p : dermatologist.getPharmacies()) {
                VacationRequest vacationRequest = VacationRequestMapper.mapToRequest(dto);
                vacationRequest.setMedicalStaff((MedicalStaff) userRepository.findById(dto.getMedicalStaffId()).get());
                vacationRequest.setPharmacy(p);
                vacationRequest.setStatus(VacationRequestStatus.PENDING);
                vacationRequestRepository.save(vacationRequest);
            }
        }

    }

    @Override
    public List<VacationRequest> getAllByPharmacy(Long pharmacyId) {
        return vacationRequestRepository.getAllPendingVacationRequests(pharmacyId);
    }

    @Override
    public void approveVacationRequest(Long vacationRequestId) throws IOException, MessagingException {
        VacationRequest vacationRequest=vacationRequestRepository.findById(vacationRequestId).get();
        vacationRequest.setStatus(VacationRequestStatus.APPROVED);
        vacationRequestRepository.save(vacationRequest);

        if (vacationRequest.getMedicalStaff().getRole().equals(Role.Values.Pharmacist)) {
            List<Counseling> counselings = appointmentRepository.findAllUpcomingForMedicalStaff(vacationRequest.getMedicalStaff().getId(), vacationRequest.getStartDate(), vacationRequest.getEndDate());
            for (Counseling c : counselings) {
                c.setCanceled(true);
                appointmentRepository.save(c);
                emailService.sendEmailForCanceledAppointmentDueVacation((Appointment) c);
            }
        } else {
            List<Examination> counselings = appointmentRepository.findAllUpcomingExaminationsForMedicalStaff(vacationRequest.getMedicalStaff().getId(), vacationRequest.getStartDate(), vacationRequest.getEndDate());
            for (Examination c : counselings) {
                c.setCanceled(true);
                appointmentRepository.save(c);
                emailService.sendEmailForCanceledAppointmentDueVacation((Appointment) c);
            }
        }
    }

    @Override
    public void denyVacationRequest(Long vacationRequestId) {
        VacationRequest vacationRequest=vacationRequestRepository.findById(vacationRequestId).get();
        vacationRequest.setStatus(VacationRequestStatus.REJECTED);
        vacationRequestRepository.save(vacationRequest);
    }
}
