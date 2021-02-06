package com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.DermatologistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PatientMapper;

import java.util.ArrayList;
import java.util.List;

public class ComplaintMapper {
    private ComplaintMapper(){}


    public static ComplaintDTO mapComplantToDTO(Complaint c){
            return new ComplaintDTO(c.getId(), PatientMapper.mapPatientToDTO(c.getPatient()), c.getText(), c.getUsertToComplainId(), c.getRole(), c.isAnswered());
    }
    public static Complaint mapDTOToComplainet(ComplaintDTO dto){
        Complaint c = new Complaint();
        c.setId(dto.getId());
        c.setPatient(PatientMapper.mapDTOToPatient(dto.getPatient()));
        c.setText(dto.getText());
        c.setUsertToComplainId(dto.getUsertToComplainId());
        c.setRole(dto.getRole());
        c.setAnswered(dto.isAnswered());
        return c;
    }

    public static List<ComplaintDTO> mapToListDTOS(List<Complaint> complaints){
        List<ComplaintDTO> dtos = new ArrayList<>();
        for(Complaint c:complaints){
            dtos.add(mapComplantToDTO(c));
        }

        return dtos;
    }

}
