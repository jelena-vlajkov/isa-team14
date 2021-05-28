package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.VacationRequestStatus;
import com.atlaspharmacy.atlaspharmacy.users.mapper.VacationRequestMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import com.atlaspharmacy.atlaspharmacy.users.service.IVacationRequestService;
import com.atlaspharmacy.atlaspharmacy.users.service.IWorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationRequestService implements IVacationRequestService {
    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;
    private final IPharmacistService pharmacistService;
    private final IDermatologistService dermatologistService;
    private final IWorkDayService workDayService;

    @Autowired
    VacationRequestService(VacationRequestRepository vacationRequestRepository, UserRepository userRepository, IPharmacistService pharmacistService, IDermatologistService dermatologistService, IWorkDayService workDayService) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.userRepository = userRepository;
        this.pharmacistService = pharmacistService;
        this.dermatologistService = dermatologistService;
        this.workDayService = workDayService;
    }

    @Override
    public void saveVacationRequest(VacationRequestDTO dto) throws Exception {
        if (!userRepository.findById(dto.getMedicalStaffId()).isPresent()) {
            throw new Exception("Invalid request!");
        }
        VacationRequest vacationRequest = VacationRequestMapper.mapToRequest(dto);

        vacationRequest.setMedicalStaff((MedicalStaff) userRepository.findById(dto.getMedicalStaffId()).get());

        vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public List<VacationRequest> getAllByPharmacy(Long pharmacyId) {
        return vacationRequestRepository.getAllPendingVacationRequests(pharmacyId);
    }

    @Override
    public void approveVacationRequest(Long vacationRequestId) {
        VacationRequest vacationRequest=vacationRequestRepository.findById(vacationRequestId).get();
        vacationRequest.setStatus(VacationRequestStatus.APPROVED);
        vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public void denyVacationRequest(Long vacationRequestId) {
        VacationRequest vacationRequest=vacationRequestRepository.findById(vacationRequestId).get();
        vacationRequest.setStatus(VacationRequestStatus.REJECTED);
        vacationRequestRepository.save(vacationRequest);
    }
}
