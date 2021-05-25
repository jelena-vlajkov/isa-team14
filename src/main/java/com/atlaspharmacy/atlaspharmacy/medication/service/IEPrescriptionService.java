package com.atlaspharmacy.atlaspharmacy.medication.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.EPrescriptionDTO;
import com.atlaspharmacy.atlaspharmacy.medication.DTO.PrescribedDrugDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import com.atlaspharmacy.atlaspharmacy.reservations.DTO.CreateDrugReservationDTO;

import java.util.List;

public interface IEPrescriptionService {
    List<EPrescription> getPatientsEPrescription(Long id);
    void saveNewPrescription(CreateDrugReservationDTO dto) throws Exception;
    List<EPrescriptionDTO> getAllEPrescritpions(Long patientId);
    List<PrescribedDrugDTO> getAllPrescribedDrugForPatient(Long patientId);
}
