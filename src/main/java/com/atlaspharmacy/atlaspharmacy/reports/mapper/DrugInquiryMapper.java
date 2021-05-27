package com.atlaspharmacy.atlaspharmacy.reports.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugInquiryReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.domain.DrugInquiryReport;

import java.util.ArrayList;
import java.util.List;

public class DrugInquiryMapper {

    public static DrugInquiryReportDTO  mapToDTO(DrugInquiryReport drugInquiry){
        DrugInquiryReportDTO dto=new DrugInquiryReportDTO();
        dto.setDate(drugInquiry.getDate());
        dto.setMedication(MedicationMapper.convertToMedicationDTO(drugInquiry.getMedication()));
        return dto;
    }

    public static List<DrugInquiryReportDTO> mapToListDTOS(List<DrugInquiryReport> drugInquiryReports){
        List<DrugInquiryReportDTO> dtos=new ArrayList();
        for(DrugInquiryReport d:drugInquiryReports){
            dtos.add(mapToDTO(d));
        }
        return dtos;
    }
}
