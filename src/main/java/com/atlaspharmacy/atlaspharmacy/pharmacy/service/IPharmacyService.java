package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import org.springframework.context.annotation.Bean;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IPharmacyService {
    Pharmacy getById(Long id);
    Pharmacy registerPharmacy(PharmacyDTO pharmacyDTO) throws Exception;
    List<PharmacyDTO> getAllPharmacies();
    List<PharmacyDTO> findByName(String name);
    List<PharmacyDTO> findByAddress(String address);
    List<Pharmacy> getPharmaciesToComplain(Long id);
    List<PharmacyDTO> getPharmaciesByMedication(Long code);
    List<PharmacyDTO> getSubscribed(Long id);
    boolean isPharamcyRegistered(String email);
}
