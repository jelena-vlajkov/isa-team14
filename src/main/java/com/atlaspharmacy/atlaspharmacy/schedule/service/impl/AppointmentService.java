package com.atlaspharmacy.atlaspharmacy.schedule.service.impl;

import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.enums.AppointmentType;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private final UserRepository _userRepository;
    private final AppointmentRepository _appointmentRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        _userRepository = userRepository;
        _appointmentRepository = appointmentRepository;
    }


    private int hoursAvailableToCancel = 3600*1000*24;
    private double cost = 1000.00;

    final static String NOT_FOUND = "Appointment could not be found!";
    final static String APPOINTMENT_NOT_FREE = "Can't schedule appointment in that specified time";

    @Override
    public Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Counseling counseling = new Counseling(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Pharmacist) _userRepository.getOne(appointmentDTO.getMedicalStaffId()), (Patient) _userRepository.getOne(appointmentDTO.getPatientId()));
            _appointmentRepository.save(counseling);
            return counseling;
        }
        throw new Exception(APPOINTMENT_NOT_FREE);
    }

    @Override
    public Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws Exception {
        if (isTimeValid(appointmentDTO.getStartDate(), appointmentDTO.getMedicalStaffId())) {
            Examination counseling = new Examination(new Period(appointmentDTO.getStartDate(), appointmentDTO.getEndDate()), cost, AppointmentType.Values.Counseling,
                    false, (Dermatologist) _userRepository.getOne(appointmentDTO.getMedicalStaffId()), (Patient) _userRepository.getOne(appointmentDTO.getPatientId()));
            _appointmentRepository.save(counseling);
            return counseling;
        }
        throw new Exception(APPOINTMENT_NOT_FREE);
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = _appointmentRepository.getOne(appointmentId);
        Date validDate = new Date(appointment.getAppointmentPeriod().getStartTime().getTime() + hoursAvailableToCancel);
        if (appointment.getAppointmentPeriod().getStartTime().before(validDate))
            return false;
        appointment.setCanceled(true);
        _appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public List<Appointment> findAvailableBy(Date date, Long medicalStaffId) {
        return null;
    }

    @Override
    public List<Appointment> findAvailableBy(Date date) {
        return null;
    }

    @Override
    public List<Appointment> findAvailableBy(Long medicalStaffId) {
        return null;
    }

    @Override
    public boolean isTimeValid(Date date, Long medicalStaffId) {
        List<Appointment> available = findAvailableBy(date, medicalStaffId);
        return false;
    }

    @Override
    public List<Appointment> getScheduledBy(Date date, Long medicalStaffId) {
        return null;
    }

    @Override
    public List<Appointment> getScheduledBy(Date date) {

        return null;
    }

    @Override
    public List<Appointment> getScheduledByMedicalStaff(Long medicalStaffId) {
        return null;
    }

    @Override
    public List<Appointment> getScheduledForPatient(Long patinetId) {
        return null;
    }

    @Override
    public List<Appointment> getScheduledForPatient(Long patinetId, Date date) {
        return null;
    }
}
