package com.atlaspharmacy.atlaspharmacy.medication.repository;

import com.atlaspharmacy.atlaspharmacy.medication.domain.EPrescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {


}
