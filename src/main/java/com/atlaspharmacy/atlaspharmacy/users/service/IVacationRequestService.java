package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;

import java.text.ParseException;
import java.util.List;

public interface IVacationRequestService {
    void saveVacationRequest(VacationRequestDTO dto) throws Exception;
    List<VacationRequest> getAllByPharmacy(Long pharmacyId);
    void approveVacationRequest(Long vacationRequestId);
    void denyVacationRequest(Long vacationRequestId);
}
