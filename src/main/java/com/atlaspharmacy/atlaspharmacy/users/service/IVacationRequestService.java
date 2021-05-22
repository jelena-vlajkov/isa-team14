package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;

import java.text.ParseException;

public interface IVacationRequestService {
    void saveVacationRequest(VacationRequestDTO dto) throws Exception;
}
