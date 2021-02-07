package com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.AnsweredComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.AnswerToComplaint;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SystemAdminMapper;

public class AnswerToComplaintMapper {
    private AnswerToComplaintMapper(){}

    public static AnswerToComplaint mapDTOToAnswer(AnsweredComplaintDTO dto){
        AnswerToComplaint a = new AnswerToComplaint();
        a.setId(dto.getId());
        a.setAnswer(dto.getAnswer());
//        a.setComplaint(dto.getComplaint());
        a.setSystemAdmin(SystemAdminMapper.mapDTOToSystemAdmin(dto.getSystemAdmin()));
        return a;
    }

    public static AnsweredComplaintDTO mapAnswerToDTO(AnswerToComplaint a){
        return new AnsweredComplaintDTO(a.getId(), a.getComplaint().getId(), a.getAnswer(), SystemAdminMapper.mapSystemAdminToDTO(a.getSystemAdmin()));
    }
}
