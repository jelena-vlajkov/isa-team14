package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidPatientData;

public interface IUserService {
    User getUserBy(Long id);
    User getUserByMail(String mail);
    User getPharmacyAdmin(Long pharmacyId);
}
