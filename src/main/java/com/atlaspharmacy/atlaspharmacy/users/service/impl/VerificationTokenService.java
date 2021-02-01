package com.atlaspharmacy.atlaspharmacy.users.service.impl;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.domain.VerificationToken;
import com.atlaspharmacy.atlaspharmacy.users.repository.VerificationTokenRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenService implements IVerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;
    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    @Transactional
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public VerificationToken findByPatient(Patient patient) {
        return verificationTokenRepository.findByPatient(patient);
    }

    public void save(Patient patient, String token){
        VerificationToken verificationToken = new VerificationToken(token, patient);
        verificationToken.setExpiryDate(calculateExpiryDate(12*60));
        verificationTokenRepository.save(verificationToken);
    }

    private Timestamp calculateExpiryDate(int expiryTime){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTime);
        return new Timestamp(cal.getTime().getTime());
    }
}
