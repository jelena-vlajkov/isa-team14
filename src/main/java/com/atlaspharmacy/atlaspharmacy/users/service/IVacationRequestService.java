package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestAnswerDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IVacationRequestService {
    void saveVacationRequest(VacationRequestDTO dto) throws Exception;
    List<VacationRequest> getAllByPharmacy(Long pharmacyId);
    void approveVacationRequest(VacationRequestAnswerDTO answer) throws IOException, MessagingException;
    void denyVacationRequest(VacationRequestAnswerDTO answer) throws IOException, MessagingException;
}
