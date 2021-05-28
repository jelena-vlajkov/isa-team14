package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.ComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.DermatologistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacistComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.PharmacyComplaintDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Complaint;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.ComplaintMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.ComplaintRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.IComplaintService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.mapper.DermatologistMapper;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacistMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.DermatologistRepository;
import com.atlaspharmacy.atlaspharmacy.users.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {
    private final ComplaintRepository complaintRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final PharmacyRepository pharmacyRepository;
    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository, PharmacyRepository pharmacyRepository) {
        this.complaintRepository = complaintRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<ComplaintDTO> getAllComplaints() {
        return ComplaintMapper.mapToListDTOS(complaintRepository.findAll());
    }
    public Complaint getById(Long id){
        return complaintRepository.findById(id).get();
    }

    @Override
    public List<ComplaintDTO> getAllUnasweredComplaints() {
        List<ComplaintDTO> unansweredComplaints = new ArrayList<>();
        for(ComplaintDTO c : getAllComplaints()){
            if(!c.isAnswered()){
                unansweredComplaints.add(c);
            }
        }
        return unansweredComplaints;
    }

    @Override
    public List<DermatologistComplaintDTO> getDermatologistComplaints(){
        List<DermatologistComplaintDTO> dtos = new ArrayList<>();
        List<Complaint> complaints = complaintRepository.getAllUnansweredComplaintsDermatologists("dermatologist");
        ComplaintDTO mapped;
        if(complaints!=null) {
            for (Complaint c : complaints) {
                mapped = ComplaintMapper.mapComplantToDTO(c);
                DermatologistComplaintDTO dto = new DermatologistComplaintDTO();
                dto.setId(c.getId());
                dto.setDermatologist(DermatologistMapper.mapDermatologistToDTO(dermatologistRepository.findById(c.getUsertToComplainId()).get()));
                dto.setPatient(mapped.getPatient());
                dto.setText(c.getText());
                dto.setAnswered(c.isAnswered());
                dtos.add(dto);

            }
        }
        return dtos;
    }
    @Override
    public List<PharmacistComplaintDTO> getPharmacistComplaints(){
        List<PharmacistComplaintDTO> dtos = new ArrayList<>();
        List<Complaint> complaints = complaintRepository.getAllUnansweredComplaintsDermatologists("pharmacist");
        ComplaintDTO mapped;
        if(complaints!=null){
            for(Complaint c : complaints){
                mapped = ComplaintMapper.mapComplantToDTO(c);
                PharmacistComplaintDTO dto = new PharmacistComplaintDTO();
                dto.setId(c.getId());
                dto.setPharmacist(PharmacistMapper.mapPharmacistToDTO(pharmacistRepository.findById(c.getUsertToComplainId()).get()));
                dto.setPatient(mapped.getPatient());
                dto.setText(c.getText());
                dto.setAnswered(c.isAnswered());
                dtos.add(dto);
            }
        }

        return dtos;
    }
    @Override
    public List<PharmacyComplaintDTO> getPharmacyComplaints(){
        List<PharmacyComplaintDTO> dtos = new ArrayList<>();
        List<Complaint> complaints = complaintRepository.getAllUnansweredComplaintsDermatologists("pharmacy");
        ComplaintDTO mapped;
        if(complaints!=null){
            for(Complaint c : complaints){
                mapped = ComplaintMapper.mapComplantToDTO(c);
                PharmacyComplaintDTO dto = new PharmacyComplaintDTO();
                dto.setId(c.getId());
                dto.setPharmacy(PharmacyMapper.mapPharmacyToDTO(pharmacyRepository.findById(c.getUsertToComplainId()).get()));
                dto.setPatient(mapped.getPatient());
                dto.setText(c.getText());
                dto.setAnswered(c.isAnswered());
                dtos.add(dto);
            }
        }

        return dtos;
    }

    @Override
    public Complaint answerToComplaint(Long id){
        Complaint c = complaintRepository.findById(id).get();
        c.setAnswered(true);
        Complaint complaint = complaintRepository.save(c);
        return complaint;
    }

    @Override
    public Complaint sendComplaint(ComplaintDTO dto) {
        Complaint c = ComplaintMapper.mapDTOToComplainet(dto);
        complaintRepository.save(c);
        return c;
    }
}
