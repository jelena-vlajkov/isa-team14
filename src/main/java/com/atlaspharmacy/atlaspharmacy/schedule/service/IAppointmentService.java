package com.atlaspharmacy.atlaspharmacy.schedule.service;

import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ScheduleAppointmentDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {

    Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws Exception;
    Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws Exception;
    boolean cancelAppointment(Long appointmentId);
    List<Appointment> findAvailableBy(Date date, Long medicalStaffId);
    List<Counseling> findAvailableCounselingsBy(Date date);
    List<Examination> findAvailableExaminationsBy(Date date);
    boolean isTimeValid(Date date, Long medicalStaffId);
    List<Appointment> getScheduledForPatient(Long patinetId);
    List<Appointment> getOccupiedBy(Date date);
    List<Appointment> getOccupiedBy(Long medicalStaffId);
    List<Appointment> getOccupiedBy(Date date, Long medicalStaffId);
    List<Appointment> initializeAppointmentTime(Date date, Long medicalStaffId);
    List<Counseling> getAllOccupiedCounselings(Date date);
    List<Examination> getAllOccupiedExaminations(Date date);




}
