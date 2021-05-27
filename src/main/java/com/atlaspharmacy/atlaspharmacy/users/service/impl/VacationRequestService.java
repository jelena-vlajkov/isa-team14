package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.VacationRequestStatus;
import com.atlaspharmacy.atlaspharmacy.users.mapper.VacationRequestMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IDermatologistService;
import com.atlaspharmacy.atlaspharmacy.users.service.IPharmacistService;
import com.atlaspharmacy.atlaspharmacy.users.service.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationRequestService implements IVacationRequestService {
    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;
    private final IPharmacistService pharmacistService;
    private final IDermatologistService dermatologistService;

    @Autowired
    VacationRequestService(VacationRequestRepository vacationRequestRepository, UserRepository userRepository, IPharmacistService pharmacistService, IDermatologistService dermatologistService) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.userRepository = userRepository;
        this.pharmacistService = pharmacistService;
        this.dermatologistService = dermatologistService;
    }

    @Override
    public void saveVacationRequest(VacationRequestDTO dto) throws Exception {
        if (!userRepository.findById(dto.getMedicalStaff().getId()).isPresent()) {
            throw new Exception("Invalid request!");
        }
        VacationRequest vacationRequest = VacationRequestMapper.mapToRequest(dto);

        vacationRequest.setMedicalStaff((MedicalStaff) userRepository.findById(dto.getMedicalStaff().getId()).get());

        vacationRequestRepository.save(vacationRequest);
    }

    @Override
    public List<VacationRequest> getAllByPharmacy(Long pharmacyId) {
        List<VacationRequest> allVacationRequests=vacationRequestRepository.findAll();
        List<VacationRequest> vacationRequestsForPharmacy = new ArrayList();
        for(VacationRequest v:allVacationRequests){
            if(v.getPharmacy().getId().equals(pharmacyId)){
                if(v.getStatus().equals(VacationRequestStatus.PENDING)){
                    vacationRequestsForPharmacy.add(v);
                }
            }
        }
        return vacationRequestsForPharmacy;
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
