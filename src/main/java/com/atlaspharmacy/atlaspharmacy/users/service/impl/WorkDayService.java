package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkDayService implements IWorkDayService {
    private final WorkDayRepository workDayRepository;
    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository) {
        this.workDayRepository = workDayRepository;
    }

    @Override
    public WorkDay getBy(Long medicalStaffId, Date date) {
        return workDayRepository.findAll()
                .stream()
                .filter(workDay -> workDay.isSameDateAndMedicalStaff(date, medicalStaffId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<WorkDay> getBy(Long medicalStaffId) {
        return workDayRepository.findAll()
                .stream()
                .filter(workDay -> workDay.isMedicalStaff(medicalStaffId))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkDay> getAll() {
        return workDayRepository.findAll();
    }

    @Override
    public List<WorkDay> getByDate(Date date) {
        return workDayRepository.findAll()
                .stream()
                .filter(workDay -> workDay.isSameDate(date))
                .collect(Collectors.toList());
    }
}
