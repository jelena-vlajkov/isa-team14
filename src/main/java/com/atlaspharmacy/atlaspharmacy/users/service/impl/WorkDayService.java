package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.WorkDayRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkDayService implements IWorkDayService {
    private final WorkDayRepository workDayRepository;
    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final VacationRequestRepository vacationRequestRepository;

    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository, PharmacyRepository pharmacyRepository, UserRepository userRepository, VacationRequestRepository vacationRequestRepository) {
        this.workDayRepository = workDayRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.userRepository = userRepository;
        this.vacationRequestRepository = vacationRequestRepository;
    }

    @Override
    public List<WorkDay> getBy(Long medicalStaffId, Date date) {
        return workDayRepository.getByMedicalStaffAndDate(medicalStaffId, date);
    }

    @Override
    public List<WorkDay> getBy(Long medicalStaffId) {
        return workDayRepository.getByMedicalStaff(medicalStaffId);
    }

    @Override
    public List<WorkDay> getAll() {
        return workDayRepository.findAllNonDisabled();
    }

    @Override
    public List<WorkDay> getByDate(Date date) {
        return workDayRepository.getByDate(date);
    }


    @Override
    public void addWorkday(WorkDayDTO workDayDTO) throws Exception {
        WorkDay workDay=new WorkDay();
        workDay.setPharmacy(pharmacyRepository.findById(workDayDTO.getPharmacy().getId()).get());
        List<VacationRequest> vacationRequest = vacationRequestRepository.getVacationRequestBy(workDayDTO.getMedicalStaff().getId(), workDayDTO.getDate());

        if (vacationRequest.size() == 0) {
            throw new Exception("Invalid request for adding work day! Staff already has vacation request.");
        }

        workDay.setDate(workDayDTO.getDate());
        workDay.setWorkDayPeriod(new Period(workDayDTO.getStartTime(),workDayDTO.getEndTime()));
        workDay.setMedicalStaff((MedicalStaff) userRepository.findById(workDayDTO.getMedicalStaff().getId()).get());
        workDayRepository.save(workDay);
    }

    @Override
    public List<WorkDay> getAllWorkDaysInPeriod(Long medicalStaffId, Date startDate, Date endDate) {
        return workDayRepository.getWorkDaysInIntervalForStaff(medicalStaffId, startDate, endDate);
    }

    @Override
    public List<WorkDay> getWorkDaysInSchedulingRangeAndStaff(Long medicalStaffId, Date startDate, Date endDate) {
        List<WorkDay> workDaysForStuff = getBy(medicalStaffId);
        List<WorkDay> workingInRange = new ArrayList<>();
        for (WorkDay workDay : workDaysForStuff) {
            if (workDay.getWorkDayPeriod().getStartTime().getTime() >= startDate.getTime()  &&
                    workDay.getWorkDayPeriod().getStartTime().getTime() < endDate.getTime()) {
                workingInRange.add(workDay);
            }else if (workDay.getWorkDayPeriod().getStartTime().getTime() == startDate.getTime()  &&
                    workDay.getWorkDayPeriod().getStartTime().getTime() == endDate.getTime()) {
                workingInRange.add(workDay);
            }else if (workDay.getWorkDayPeriod().getStartTime().getTime() < startDate.getTime() &&
                     workDay.getWorkDayPeriod().getEndTime().getTime() >= startDate.getTime()) {
                workingInRange.add(workDay);
            }
        }

        return  workingInRange;
    }

    @Override
    public void updateWorkDay(Long id) {
        WorkDay workDay = workDayRepository.findById(id).get();
        workDay.setDisabled(true);
        workDayRepository.save(workDay);
    }

}
