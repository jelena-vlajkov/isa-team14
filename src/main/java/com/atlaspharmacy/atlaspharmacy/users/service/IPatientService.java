package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IPatientService {
    Patient registerPatient(PatientDTO patientDTO) throws Exception;
    Patient enablePatient(String token) ;
    void editPatient(PatientDTO patientDTO);
}
