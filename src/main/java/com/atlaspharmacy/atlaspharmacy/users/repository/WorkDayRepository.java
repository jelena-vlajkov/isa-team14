package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1")
    List<WorkDay> getByMedicalStaff(Long medicalStaff);

    @Query(value = "SELECT w FROM WorkDay w WHERE w.medicalStaff.id = ?1 AND CAST(w.date as date) = CAST(?2 as date)")
    WorkDay getByMedicalStaffAndDate(Long medicalStaff, Date date);

    @Query(value = "SELECT w FROM WorkDay w WHERE CAST(w.date as date) = CAST(?1 as date)")
    List<WorkDay> getByDate(Date date);
}
