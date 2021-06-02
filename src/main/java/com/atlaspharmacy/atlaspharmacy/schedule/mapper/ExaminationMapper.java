package com.atlaspharmacy.atlaspharmacy.schedule.mapper;

import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.DTO.ExaminationDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;

import java.util.ArrayList;
import java.util.List;

public class ExaminationMapper {
    public static ExaminationDTO mapToDTO(Examination examination){
        return new ExaminationDTO(examination.getId(), new PeriodDTO(examination.getAppointmentPeriod().getStartTime(),examination.getAppointmentPeriod().getEndTime())
                ,examination.getCost(), examination.isCanceled()
                , PatientMapper.mapPatientToDTO(examination.getPatient())
                , PharmacyMapper.mapPharmacyToDTO(examination.getPharmacy())
                , DermatologistMapper.mapDermatologistToDTO(examination.getDermatologist()));
    }

    public static List<ExaminationDTO> mapToListDTO(List<Examination> examinationList){
        List<ExaminationDTO> dtos = new ArrayList<>();
        for(Examination e:examinationList){
            dtos.add(mapToDTO(e));
        }
        return dtos;
    }
}
