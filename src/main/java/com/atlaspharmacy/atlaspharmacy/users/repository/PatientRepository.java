package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT p FROM Patient p WHERE p.verificationCode = ?1")
    Patient findByVerificationCode(String code);

    @Query(value = "SELECT p FROM Patient p WHERE p.email = ?1")
    Patient findByEmail(String email);
}
