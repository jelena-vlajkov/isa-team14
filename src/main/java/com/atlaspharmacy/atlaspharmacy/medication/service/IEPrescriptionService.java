package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.PrescribedDrugDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;

import java.util.List;

public interface IEPrescriptionService {
    List<EPrescription> getPatientsEPrescription(Long id);
    List<EPrescriptionDTO> getAllEPrescritpions(Long patientId);
    List<PrescribedDrugDTO> getAllPrescribedDrugForPatient(Long patientId);
}
