package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
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
        vacationRequest.setPharmacy(PharmacyMapper.mapDTOToPharmacy(dto.getPharmacy()));
        return vacationRequest;
    }

    public static VacationRequestDTO mapToDTO(VacationRequest vacationRequest){
       return new VacationRequestDTO(vacationRequest.getId(),vacationRequest.getMedicalStaff().getId(),vacationRequest.getStartDate()
               ,vacationRequest.getEndDate(),vacationRequest.getVacationReason(),MedicalStaffMapper.MapMedicalStaffToDTO(vacationRequest.getMedicalStaff())
               ,PharmacyMapper.mapPharmacyToDTO(vacationRequest.getPharmacy()));

    }

    public static List<VacationRequestDTO> mapToListDTOS(List<VacationRequest> vacationRequests){
        List<VacationRequestDTO> dtos=new ArrayList();
        for(VacationRequest v:vacationRequests){
            dtos.add(mapToDTO(v));
        }
        return dtos;
    }
}
