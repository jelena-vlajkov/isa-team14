package com.atlaspharmacy.atlaspharmacy.schedule.service;

import com.atlaspharmacy.atlaspharmacy.schedule.DTO.*;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.AppointmentNotFreeException;
import com.atlaspharmacy.atlaspharmacy.schedule.exceptions.InvalidMedicalStaff;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {

    Appointment scheduleCounseling(ScheduleAppointmentDTO appointmentDTO) throws Exception;
    Appointment scheduleExamination(ScheduleAppointmentDTO appointmentDTO) throws Exception;
    Appointment saveAppointment(ScheduleAppointmentDTO scheduleAppointmentDTO) throws Exception;
    boolean cancelAppointment(Long appointmentId);
    List<Appointment> findAvailableBy(Date date, Long medicalStaffId);
    List<Counseling> findAvailableCounselingsBy(Date date);
    List<Examination> findAvailableExaminationsBy(Date date);
    boolean isTimeValid(Date date, Long medicalStaffId);
    List<Appointment> getScheduledForPatient(Long patinetId);
    List<Appointment> getOccupiedBy(Date date);
    List<Appointment> getOccupiedBy(Long medicalStaffId);
    List<AppointmentDTO> getOccupiedBy2(Long medicalStaffId);
    List<Appointment> getOccupiedBy(Date date, Long medicalStaffId);
    List<Appointment> initializeAppointmentTime(Date date, Long medicalStaffId);
    List<Counseling> getAllOccupiedCounselings(Date date);
    List<Examination> getAllOccupiedExaminations(Date date);
    List<Counseling> getFinishedPatientsCounselings(Long id);
    List<Examination> getFinishedPatientsExaminations(Long id);
    List<Appointment> getAllFinishedAppointmentsForPatient(Long patientId);
    int getNumberOfScheduledByDate(Date date);
    List<Integer> getNumberOfAppointmentsForMonth(int month, int year);
    List<Integer> getNumberOfAppointmentsForHalfYear(int part, int year);
    List<Integer> getNumberOfAppointmentsForMonth(int year);
    boolean occupiedExaminationExists(Long dermatologistId, Long pharmacyId);
    boolean occupiedCounselingsExists(Long pharmacistId);
    List<Examination> findAvailableExaminationsForDermatologist(Long medicalStaffId, Long pharmacyId);
    List<PatientsOverviewDTO> getPatientsByMedicalStaff(Long medicalStaffId) throws InvalidMedicalStaff, Exception;
    List<Appointment> findAvailableByEmployeeAndPharmacy(Long pharmacyId, Long employeeId, Date date);
    List<AppointmentDTO> finishedAppointmentExamination(Long patientId);
    List<AppointmentDTO> finishedAppointmentCounseling(Long patientId);
    List<AppointmentDTO> getNotFinishedAppointmentsForPatient(Long patientId);
    List<PatientsOverviewDTO> SearchPatientsByParameters(SearchParametersDTO searchParametersDTO) throws InvalidMedicalStaff, Exception;
    void finishAppointment(Long appointmentId) throws Exception;
    Appointment findSpecificAppointment(Date dateObj, Long medicalStaffId, Long patientId) throws Exception;
    List<Appointment> findAvailableForPatient(PatientAppointmentDTO dto) throws Exception;

    List<Appointment> getScheduledByMonth(Date date, Long id);
}
