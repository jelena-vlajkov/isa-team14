package com.atlaspharmacy.atlaspharmacy.medication.service.implementations;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.EPrescriptionMapper;
import com.atlaspharmacy.atlaspharmacy.medication.repository.EPrescriptionRepository;
import com.atlaspharmacy.atlaspharmacy.medication.service.IEPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EPrescriptionService implements IEPrescriptionService {
    private final EPrescriptionRepository ePrescriptionRepository;

    @Autowired
    public EPrescriptionService(EPrescriptionRepository ePrescriptionRepository) {
        this.ePrescriptionRepository = ePrescriptionRepository;
    }

    @Override
    public List<EPrescription> getPatientsEPrescription(Long id) {
        List<EPrescription> allEprescriptions = ePrescriptionRepository.findAll();
        List<EPrescription> patientsEPrescriptions = new ArrayList<>();
        for(EPrescription ep : allEprescriptions){
            if(ep.getPatient().getId().equals(id)){
                patientsEPrescriptions.add(ep);
            }
        }

        return patientsEPrescriptions;
    }

    @Override
    public List<EPrescriptionDTO> getAllEPrescritpions(Long patientId) {
        List<EPrescriptionDTO> ePrescriptionDTOS = new ArrayList<>();
        List<EPrescription> ePrescriptions = getPatientsEPrescription(patientId);
        for (EPrescription e : ePrescriptions) {
            ePrescriptionDTOS.add(EPrescriptionMapper.mapEPrescriptionToDTO(e));
        }

        return ePrescriptionDTOS;

    }
}
