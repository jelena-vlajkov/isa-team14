package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.MedicalStaff;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.mapper.VacationRequestMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.UserRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.VacationRequestRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IVacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class VacationRequestService implements IVacationRequestService {
    private final VacationRequestRepository vacationRequestRepository;
    private final UserRepository userRepository;

    @Autowired
    VacationRequestService(VacationRequestRepository vacationRequestRepository, UserRepository userRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.userRepository = userRepository;
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
}
