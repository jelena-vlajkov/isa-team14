package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1 AND w.disabled = false")
    List<WorkDay> getByMedicalStaff(Long medicalStaff);

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1 AND CAST(w.date as date) = CAST(?2 as date) AND w.disabled = false")
    List<WorkDay> getByMedicalStaffAndDate(Long medicalStaff, Date date);

    @Query(value = "SELECT w FROM WorkDay w WHERE CAST(w.date as date) = CAST(?1 as date) AND w.disabled = false")
    List<WorkDay> getByDate(Date date);

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1 AND w.date >= ?2 AND w.date <= ?3 AND w.disabled = false")
    List<WorkDay> getWorkDaysInIntervalForStaff(Long medicalStaffId, Date startDate, Date endDate);

    @Query(value = "SELECT w FROM WorkDay w WHERE w.disabled = false")
    List<WorkDay> findAllNonDisabled();

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id= ?1 AND w.date > current_date AND w.pharmacy.id = ?2")
    List<WorkDay> findUpcomingByStaff(Long medicalStaffId, Long pharmacyId);

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1 AND w.pharmacy.id = ?2")
    List<WorkDay> getAllByStaffAndPharmacy(Long medicalStaffId, Long pharmacyId);
}
