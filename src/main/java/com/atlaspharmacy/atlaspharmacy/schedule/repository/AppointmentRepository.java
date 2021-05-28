package com.atlaspharmacy.atlaspharmacy.schedule.repository;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Appointment;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Counseling;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.security.AccessControlContext;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = 'Counseling'")
    List<Appointment> findAllCounselings();

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = 'Examination'")
    List<Appointment> findAllExaminations();

    @Query(value = "SELECT a FROM Appointment a WHERE a.pharmacy.id = ?1")
    List<Appointment> findAllAppointmentsByPharmacy(Long pharmacyId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.patient.id = ?2")
    List<Appointment> findAppointmentsByPatient(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Appointment> getAppointmentsByDate(Date date);

    @Query(value = "SELECT a FROM Appointment a WHERE a.patient.id = ?2 AND CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Appointment> getAppointmentByDateAndPatient(Date date, Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?2 AND CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Appointment> getAllAppointmentsByDateForSpecificType(Date date, String type);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?1 AND a.appointmentPeriod.endTime < current_date AND a.patient.id = ?2")
    List<Appointment> getAllAppointmentsFinishedForSpecificTypeAndPatient(String type, Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPeriod.endTime < current_date AND a.patient.id = ?1")
    List<Appointment> getAllFinishedAppointments(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPeriod.endTime > current_date AND a.patient.id = ?1 AND a.isCanceled = false")
    List<Appointment> getNotFinishedAppointmentsForPatient(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?1 AND a.patient.id = ?2 AND a.appointmentPeriod.endTime <= current_date")
    List<Appointment> getAllFinishedForPatientAndType(String type, Long patientId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1")
    List<Counseling> getAllCounselingsByPharmacist(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND CAST(c.appointmentPeriod.startTime as date) = CAST(?2 as date)")
    List<Counseling> getAllCounselingsByPharmacistAndDate(Long pharmacistId, Date date);

    @Query(value = "SELECT c FROM Counseling c WHERE CAST(c.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Counseling> getAllOccupiedCounselingsByDate(Date date);

    @Query(value = "SELECT e FROM Examination e WHERE CAST(e.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Examination> getAllOccupiedExaminations(Date date);

    @Query(value = "SELECT c FROM Counseling c WHERE c.appointmentPeriod.endTime <= current_date AND c.patient.id = ?1")
    List<Counseling> getFinishedCounselingsByPatient(Long patientId);

    @Query(value = "SELECT c FROM Examination c WHERE c.appointmentPeriod.endTime <= current_date AND c.patient.id = ?1")
    List<Examination> getFinishedExaminations(Long patientId);

    @Query(value = "SELECT c FROM Examination c WHERE c.pharmacy.id = ?2 AND c.dermatologist.id = ?1")
    List<Examination> getOccupiedExaminationsByDermatologistAndPharmacy(Long dermatologistId, Long pharmacyId);

    @Query(value = "SELECT a FROM Examination a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Examination> getAllExaminationsByDate(Date date);

    @Query(value = "SELECT a FROM Counseling a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date)")
    List<Counseling> getAllOccupiedCounselings(Date date);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1")
    List<Examination> getAllExaminationsByDermatologist(Long dermatologistId);






}
