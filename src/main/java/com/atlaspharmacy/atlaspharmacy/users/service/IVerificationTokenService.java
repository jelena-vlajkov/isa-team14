package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.VerificationToken;
import com.atlaspharmacy.atlaspharmacy.users.repository.VerificationTokenRepository;

public interface IVerificationTokenService {
    VerificationToken findByToken(String token);
    VerificationToken findByPatient(Patient patient);
}
