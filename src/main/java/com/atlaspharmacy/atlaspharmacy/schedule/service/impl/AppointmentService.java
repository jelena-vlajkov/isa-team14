package com.atlaspharmacy.atlaspharmacy.schedule.service.impl;

import com.atlaspharmacy.atlaspharmacy.appointment.domain.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.repository.AppointmentRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    final static String NOT_FOUND = "Appointment could not be found!";
    final static String APPOINTMENT_NOT_FREE = "Can't schedule appointment in that specified time";


    @Override
    public Appointment scheduleAppointment(ScheduleAppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        return false;
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
    public boolean isTimeValid(Period period) {
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
