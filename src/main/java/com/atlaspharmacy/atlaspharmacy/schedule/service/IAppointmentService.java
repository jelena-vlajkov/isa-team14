package com.atlaspharmacy.atlaspharmacy.schedule.service;

import com.atlaspharmacy.atlaspharmacy.appointment.domain.Period;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {

    Appointment scheduleAppointment(ScheduleAppointmentDTO appointmentDTO);
    boolean cancelAppointment(Long appointmentId);
    List<Appointment> findAvailableBy(Date date, Long medicalStaffId);
    List<Appointment> findAvailableBy(Date date);
    List<Appointment> findAvailableBy(Long medicalStaffId);
    boolean isTimeValid(Period period);
    List<Appointment> getScheduledBy(Date date, Long medicalStaffId);
    List<Appointment> getScheduledBy(Date date);
    List<Appointment> getScheduledByMedicalStaff(Long medicalStaffId);
    List<Appointment> getScheduledForPatient(Long patinetId);
    List<Appointment> getScheduledForPatient(Long patinetId, Date date);



}
