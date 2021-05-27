package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;

import java.util.Date;
import java.util.List;

public interface IWorkDayService {
    WorkDay getBy(Long medicalStaffId, Date date);
    List<WorkDay> getBy(Long medicalStaffId);
    List<WorkDay> getAll();
    List<WorkDay> getByDate(Date date);
    void addWorkday(WorkDayDTO workDayDTO);
}
