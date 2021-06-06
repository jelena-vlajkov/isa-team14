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

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = 'Counseling' AND a.isCanceled = false")
    List<Appointment> findAllCounselings();

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = 'Examination' AND a.isCanceled = false")
    List<Appointment> findAllExaminations();

    @Query(value = "SELECT a FROM Appointment a WHERE a.pharmacy.id = ?1")
    List<Appointment> findAllAppointmentsByPharmacy(Long pharmacyId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.patient.id = ?1 AND a.isCanceled = false")
    List<Appointment> findAppointmentsByPatient(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false")
    List<Appointment> getAppointmentsByDate(Date date);

    @Query(value = "SELECT a FROM Examination a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false AND a.appointmentPeriod.endTime <= current_date AND a.pharmacy.id = ?2")
    List<Appointment> getAppointmentsByDateForBusinessReports(Date date,Long pharmacyId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.patient.id = ?2 AND CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false")
    List<Appointment> getAppointmentByDateAndPatient(Date date, Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?2 AND CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false")
    List<Appointment> getAllAppointmentsByDateForSpecificType(Date date, String type);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?1 AND a.appointmentPeriod.endTime < current_date AND a.patient.id = ?2 AND a.isCanceled = false")
    List<Appointment> getAllAppointmentsFinishedForSpecificTypeAndPatient(String type, Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPeriod.endTime < current_date AND a.patient.id = ?1 AND a.isCanceled = false")
    List<Appointment> getAllFinishedAppointments(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPeriod.endTime > current_date AND a.patient.id = ?1 AND a.isCanceled = false AND a.isCanceled = false")
    List<Appointment> getNotFinishedAppointmentsForPatient(Long patientId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.type = ?1 AND a.patient.id = ?2 AND a.appointmentPeriod.endTime <= current_date AND a.isCanceled = false")
    List<Appointment> getAllFinishedForPatientAndType(String type, Long patientId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND c.isCanceled = false")
    List<Counseling> getAllCounselingsByPharmacist(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND c.isCanceled = false ORDER BY c.patient.name DESC")
    List<Counseling> getAllCounselingsByPharmacistNameDesc(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND c.isCanceled = false ORDER BY c.patient.surname DESC")
    List<Counseling> getAllCounselingsByPharmacistSurnameDesc(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND c.isCanceled = false ORDER BY c.appointmentPeriod.startTime ASC")
    List<Counseling> getAllCounselingsByPharmacistDateAsc(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND c.isCanceled = false ORDER BY c.appointmentPeriod.startTime DESC")
    List<Counseling> getAllCounselingsByPharmacistDateDesc(Long pharmacistId);

    @Query(value = "SELECT c FROM Counseling c WHERE c.pharmacist.id = ?1 AND CAST(c.appointmentPeriod.startTime as date) = CAST(?2 as date) AND c.isCanceled = false")
    List<Counseling> getAllCounselingsByPharmacistAndDate(Long pharmacistId, Date date);

    @Query(value = "SELECT c FROM Counseling c WHERE CAST(c.appointmentPeriod.startTime as date) = CAST(?1 as date) AND c.isCanceled = false")
    List<Counseling> getAllOccupiedCounselingsByDate(Date date);

    @Query(value = "SELECT e FROM Examination e WHERE CAST(e.appointmentPeriod.startTime as date) = CAST(?1 as date) AND e.isCanceled = false")
    List<Examination> getAllOccupiedExaminations(Date date);

    @Query(value = "SELECT c FROM Counseling c WHERE c.appointmentPeriod.endTime <= current_date AND c.patient.id = ?1 AND c.isCanceled = false")
    List<Counseling> getFinishedCounselingsByPatient(Long patientId);

    @Query(value = "SELECT c FROM Examination c WHERE c.appointmentPeriod.endTime <= current_date AND c.patient.id = ?1 AND c.isCanceled = false")
    List<Examination> getFinishedExaminations(Long patientId);

    @Query(value = "SELECT c FROM Examination c WHERE c.pharmacy.id = ?2 AND c.dermatologist.id = ?1 AND c.isCanceled = false")
    List<Examination> getOccupiedExaminationsByDermatologistAndPharmacy(Long dermatologistId, Long pharmacyId);

    @Query(value = "SELECT a FROM Examination a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false AND a.isCanceled = false")
    List<Examination> getAllExaminationsByDate(Date date);

    @Query(value = "SELECT a FROM Counseling a WHERE CAST(a.appointmentPeriod.startTime as date) = CAST(?1 as date) AND a.isCanceled = false")
    List<Counseling> getAllOccupiedCounselings(Date date);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false")
    List<Examination> getAllExaminationsByDermatologist(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.appointmentPeriod.startTime ASC")
    List<Examination> getAllExaminationsByDermatologistByDateAsc(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.appointmentPeriod.startTime DESC ")
    List<Examination> getAllExaminationsByDermatologistByDateDesc(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.patient.name DESC ")
    List<Examination> getAllExaminationsByDermatologistByNameDesc(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.patient.name ASC ")
    List<Examination> getAllExaminationsByDermatologistByNameAsc(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.patient.surname DESC ")
    List<Examination> getAllExaminationsByDermatologistBySurnameDesc(Long dermatologistId);

    @Query(value = "SELECT e FROM Examination e WHERE e.dermatologist.id = ?1 AND e.isCanceled = false ORDER BY e.patient.surname ASC ")
    List<Examination> getAllExaminationsByDermatologistBySurnameAsc(Long dermatologistId);

    @Query(value = "SELECT a FROM Counseling a WHERE a.pharmacist.id = ?1 AND CAST(a.appointmentPeriod.startTime as date) >= CAST(?2 as date) AND CAST(a.appointmentPeriod.startTime as date) <= CAST(?3 as date) AND a.isCanceled = false")
    List<Counseling> findAllUpcomingForMedicalStaff(Long medicalStaffId, Date startDate, Date endDate);

    @Query(value = "SELECT a FROM Examination a WHERE a.dermatologist.id = ?1 AND CAST(a.appointmentPeriod.startTime as date) >= CAST(?2 as date) AND CAST(a.appointmentPeriod.startTime as date) <= CAST(?3 as date) AND a.isCanceled = false")
    List<Examination> findAllUpcomingExaminationsForMedicalStaff(Long medicalStaffId, Date startDate, Date endDate);

    @Query(value = "SELECT a FROM Appointment a WHERE a.patient.id = ?1 AND CAST(a.appointmentPeriod.startTime as date) >= current_date")
    List<Appointment> findUpcomingForPatient(Long patientId);

    @Query(value = "SELECT e FROM Examination e WHERE (e.dermatologist.id = ?3 and e.appointmentPeriod.startTime = ?1 and e.appointmentPeriod.endTime = ?2)" +
            "OR (e.dermatologist.id = ?3 and e.appointmentPeriod.startTime >= ?1 and e.appointmentPeriod.startTime <= ?2) or " +
            "(e.dermatologist.id = ?3 and e.appointmentPeriod.endTime >= ?1 and e.appointmentPeriod.endTime <= ?2)")
    List<Examination> overlappingExaminations(Date startTime, Date endTime, Long medicalStaffId);

    @Query(value = "SELECT e FROM Counseling e WHERE (e.pharmacist.id = ?3 and e.appointmentPeriod.startTime = ?1 and e.appointmentPeriod.endTime = ?2)" +
            "OR (e.pharmacist.id = ?3 and e.appointmentPeriod.startTime >= ?1 and e.appointmentPeriod.startTime <= ?2) or " +
            "(e.pharmacist.id = ?3 and e.appointmentPeriod.endTime >= ?1 and e.appointmentPeriod.endTime <= ?2)")
    List<Counseling> ovelappingCunselings(Date startTime, Date endTime, Long medicalStaffId);
}
