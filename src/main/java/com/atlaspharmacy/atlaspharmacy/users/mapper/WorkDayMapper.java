package com.atlaspharmacy.atlaspharmacy.users.mapper;

import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.WorkDayDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.domain.WorkDay;
import org.hibernate.jdbc.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkDayMapper {
    private WorkDayMapper(){}

    public static WorkDayDTO mapToDTO(WorkDay workDay){
        WorkDayDTO dto=new WorkDayDTO();
        dto.setDate(workDay.getDate());
        dto.setPharmacy(PharmacyMapper.mapPharmacyToDTO(workDay.getPharmacy()));
        dto.setEndTime(workDay.getWorkDayPeriod().getEndTime());
        dto.setStartTime(workDay.getWorkDayPeriod().getStartTime());
        dto.setMedicalStaff(MedicalStaffMapper.MapMedicalStaffToDTO(workDay.getMedicalStaff()));
        return dto;
    }

    public static List<WorkDayDTO> mapToListDTO(List<WorkDay> workDayList){
        List<WorkDayDTO> dtos=new ArrayList();
        for(WorkDay w:workDayList){
            dtos.add(mapToDTO(w));
        }
        return dtos;
    }

}
