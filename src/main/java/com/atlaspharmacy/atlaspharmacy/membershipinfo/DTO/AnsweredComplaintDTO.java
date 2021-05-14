package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SystemAdminDTO;

public class AnsweredComplaintDTO {
    private Long id;
    private Long complaintId;
    private String answer;
    private SystemAdminDTO systemAdmin;

    public AnsweredComplaintDTO() {

    }

    public AnsweredComplaintDTO(Long id, Long complaintId, String answer, SystemAdminDTO systemAdminDTO) {
        this.id = id;
        this.complaintId = complaintId;
        this.answer = answer;
        this.systemAdmin = systemAdminDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplaint() {
        return complaintId;
    }

    public void setComplaint(Long complaint) {
        this.complaintId = complaint;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SystemAdminDTO getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(SystemAdminDTO systemAdmin) {
        this.systemAdmin = systemAdmin;
    }
}
