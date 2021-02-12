package com.atlaspharmacy.atlaspharmacy.membershipinfo.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.DermatologistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacyComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;

import java.util.List;

public interface IComplaintService {
    List<ComplaintDTO> getAllComplaints();
    List<ComplaintDTO> getAllUnasweredComplaints();
    Complaint answerToComplaint(Long id);
    Complaint sendComplaint(ComplaintDTO dto);
    List<DermatologistComplaintDTO> getDermatologistComplaints();
    List<PharmacistComplaintDTO> getPharmacistComplaints();
    List<PharmacyComplaintDTO> getPharmacyComplaints();
}
