package com.atlaspharmacy.atlaspharmacy.schedule.service.impl;

import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.*;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Role;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final WorkDayService workDayService;

    private int hoursAvailableToCancel = 3600*1000*24;
    private int appointmentDuration = 30*60000;
    private double cost = 1000.00;

    private final static String APPOINTMENT_NOT_FREE = "Can't schedule appointment in that specified time";

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, WorkDayService workDayService) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.workDayService = workDayService;
    }


    @Override
    public Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Counseling counseling = new Counseling(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Pharmacist) userRepository.getOne(appointmentDTO.getMedicalStaffId()), (Patient) userRepository.getOne(appointmentDTO.getPatientId()));
            appointmentRepository.save(counseling);
            return counseling;
        }
        throw new Exception(APPOINTMENT_NOT_FREE);
    }

    @Override
    public Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Examination counseling = new Examination(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Dermatologist) userRepository.getOne(appointmentDTO.getMedicalStaffId()), (Patient) userRepository.getOne(appointmentDTO.getPatientId()));
            appointmentRepository.save(counseling);
            return counseling;
        }
        throw new Exception(APPOINTMENT_NOT_FREE);
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        if (appointment.canCancel())
            return false;
        appointment.setCanceled(true);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public List<Appointment> findAvailableBy(Date date, Long medicalStaffId) {
        List<Appointment> allOcupied = getOccupiedBy(date, medicalStaffId);
        List<Appointment> allFree = initializeAppointmentTime(date, medicalStaffId);
        List<Appointment> available = new ArrayList<>(allFree);
        for(Appointment appointment : allFree)
        {
            allOcupied.stream().filter((a) -> a.isOccupied(appointment.getAppointmentPeriod()))
                    .findFirst()
                    .orElse(null);
            if (appointment != null)
                available.remove(appointment);
        }
        return available;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Counseling> findAvailableCounselingsBy(Date date) {
        List<WorkDay> allWorkingStaff = workDayService.getByDate(date);
        List<Counseling> counselings = new ArrayList<>();
        for (WorkDay workDay : allWorkingStaff) {
            if (workDay.isPharmacist())
                counselings.addAll((List<Counseling>)(List<?>) findAvailableBy(date, workDay.getMedicalStaff().getId()));
        }
        return counselings;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Examination> findAvailableExaminationsBy(Date date) {
        List<WorkDay> allWorkingStaff = workDayService.getByDate(date);
        List<Examination> examinations = new ArrayList<>();
        for (WorkDay workDay : allWorkingStaff) {
            if (workDay.isDermatologist())
                examinations.addAll((List<Examination>)(List<?>) findAvailableBy(date, workDay.getMedicalStaff().getId()));
        }
        return examinations;
    }


    @Override
    public boolean isTimeValid(Date date, Long medicalStaffId) {
        List<Appointment> available = findAvailableBy(date, medicalStaffId);
        return available.stream()
                .anyMatch(appointment -> appointment.isOccupied(new Period(date, new Date(date.getTime() + appointmentDuration))));
    }

    @Override
    public List<Appointment> getScheduledForPatient(Long patinetId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isPatient(patinetId))
                .collect(Collectors.toList());
    }


    @Override
    public List<Appointment> getOccupiedBy(Date date) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isSameDay(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getOccupiedBy(Long medicalStaffId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isMedicalStaff(medicalStaffId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getOccupiedBy(Date date, Long medicalStaffId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isMedicalStaffAndDate(medicalStaffId, date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> initializeAppointmentTime(Date date, Long medicalStaffId) {
        List<Appointment> appointments = new ArrayList<>();
        WorkDay workDay = workDayService.getBy(medicalStaffId, date);

        if (workDay == null)
            return appointments;

        int startTime = workDay.getStartTime();
        int endTime = workDay.getEndTime();
        Date appointmentStart = new Date(date.getYear(), date.getMonth(), date.getDate(), startTime, 0, 0);

        for (int i = 0; i < endTime - 1; i++)
        {
            Appointment appointment = new Appointment(new Period(appointmentStart,
                    new Date(appointmentStart.getTime() + appointmentDuration)),
                    cost, "", false, null);
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public List<Counseling> getAllOccupiedCounselings(Date date) {
        List<Appointment> occupiedAppointments = appointmentRepository.findAll();
        List<Counseling> counselings = new ArrayList<>();
        for (Appointment appointment : occupiedAppointments) {
            if (appointment.isCounseling() && appointment.isSameDay(date))
                counselings.add((Counseling) appointment);
        }
        return counselings;
    }

    @Override
    public List<Examination> getAllOccupiedExaminations(Date date) {
        List<Appointment> occupiedAppointments = appointmentRepository.findAll();
        List<Examination> examinations = new ArrayList<>();
        for (Appointment appointment : occupiedAppointments) {
            if (appointment.isExamination() && appointment.isSameDay(date))
                examinations.add((Examination) appointment);
        }
        return examinations;
    }
}
