package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.users.DTO.VacationRequestDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.VacationRequest;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.VacationRequestStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VacationRequestMapper {
    public VacationRequestMapper() {}

    public static VacationRequest mapToRequest(VacationRequestDTO dto) throws ParseException {
        VacationRequest vacationRequest = new VacationRequest();
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();
        vacationRequest.setVacationReason(dto.getVacationReason());
        vacationRequest.setStartDate(startDate);
        vacationRequest.setEndDate(endDate);
        vacationRequest.setStatus(VacationRequestStatus.PENDING);
        return vacationRequest;
    }

    public static VacationRequestDTO mapToDTO(VacationRequest vacationRequest){
        VacationRequestDTO dto=new VacationRequestDTO();
        dto.setId(vacationRequest.getId());
        dto.setVacationReason(vacationRequest.getVacationReason());
        dto.setEndDate(vacationRequest.getEndDate());
        dto.setStartDate(vacationRequest.getStartDate());
        dto.setMedicalStaffDTO(MedicalStaffMapper.MapMedicalStaffToDTO(vacationRequest.getMedicalStaff()));
        return dto;
    }

    public static List<VacationRequestDTO> mapToListDTOS(List<VacationRequest> vacationRequests){
        List<VacationRequestDTO> dtos=new ArrayList();
        for(VacationRequest v:vacationRequests){
            dtos.add(mapToDTO(v));
        }
        return dtos;
    }
}
