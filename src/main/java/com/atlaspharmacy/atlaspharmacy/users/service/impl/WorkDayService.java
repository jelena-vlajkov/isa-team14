package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkDayService implements IWorkDayService {
    private final WorkDayRepository workDayRepository;
    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository, PharmacyRepository pharmacyRepository, UserRepository userRepository) {
        this.workDayRepository = workDayRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
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

    @Override
    public void addWorkday(WorkDayDTO workDayDTO) {
        WorkDay workDay=new WorkDay();
        workDay.setPharmacy(pharmacyRepository.findById(workDayDTO.getPharmacy().getId()).get());
        workDay.setDate(workDayDTO.getDate());workDay.setWorkDayPeriod(new Period(workDayDTO.getStartTime(),workDayDTO.getEndTime()));
        workDay.setMedicalStaff((MedicalStaff) userRepository.findById(workDayDTO.getMedicalStaff().getId()).get());
        workDayRepository.save(workDay);
    }

    @Override
    public boolean addWorkdayForDermatologist(WorkDayDTO workDayDTO) {
        List<WorkDay> allWorkDaysForDermatologist=getBy(workDayDTO.getMedicalStaff().getId());
        if(allWorkDaysForDermatologist.stream()
                .anyMatch(w-> w.isOccupied(new Period(workDayDTO.getStartTime(),workDayDTO.getEndTime()))))
        {
            WorkDay workDay = new WorkDay();
            workDay.setPharmacy(pharmacyRepository.findById(workDayDTO.getPharmacy().getId()).get());
            workDay.setDate(workDayDTO.getDate());
            workDay.setWorkDayPeriod(new Period(workDayDTO.getStartTime(), workDayDTO.getEndTime()));
            workDay.setMedicalStaff((MedicalStaff) userRepository.findById(workDayDTO.getMedicalStaff().getId()).get());
            workDayRepository.save(workDay);
            return true;
        }
        return false;
    }
}
