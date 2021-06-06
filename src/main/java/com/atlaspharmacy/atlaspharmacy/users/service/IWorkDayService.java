package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import org.hibernate.jdbc.Work;

import java.util.Date;
import java.util.List;

public interface IWorkDayService {
    List<WorkDay> getBy(Long medicalStaffId, Date date);
    List<WorkDay> getBy(Long medicalStaffId);
    List<WorkDay> getAll();
    List<WorkDay> getByDate(Date date);
    void addWorkday(WorkDayDTO workDayDTO) throws Exception;
    List<WorkDay> getAllWorkDaysInPeriod(Long medicalStaffId, Date startDate, Date endDate);
    void updateWorkDay(Long id);
    List<WorkDay> getWorkDaysInSchedulingRangeAndStaff(Long medicalStaffId, Date startDate, Date endDate);
    List<WorkDay> getUpcomingStaff(Long medicalStaffId, Long pharmacyId);
    void deleteAllWorkdaysForMedicalStaffAndPharmacy(Long medicalStaffId,Long pharmacyId);
}
