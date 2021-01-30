package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import org.springframework.stereotype.Service;

public interface IPatientService {
    Patient registerPatient(PatientDTO patientDTO) throws InvalidPatientData;

}
