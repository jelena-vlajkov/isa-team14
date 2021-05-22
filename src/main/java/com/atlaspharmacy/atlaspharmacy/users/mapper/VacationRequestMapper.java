package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VacationRequestMapper {
    public VacationRequestMapper() {}

    public static VacationRequest mapToRequest(VacationRequestDTO dto) throws ParseException {
        VacationRequest vacationRequest = new VacationRequest();
        Date startDate = new SimpleDateFormat("dd.MM.yyyy.").parse(dto.getStartDate());
        Date endDate = new SimpleDateFormat("dd.MM.yyyy.").parse(dto.getEndDate());
        vacationRequest.setVacationReason(dto.getVacationReason());
        vacationRequest.setStartDate(startDate);
        vacationRequest.setEndDate(endDate);
        return vacationRequest;
    }
}
