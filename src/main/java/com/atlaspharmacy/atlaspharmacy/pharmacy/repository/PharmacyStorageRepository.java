package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyStorageRepository extends JpaRepository<PharmacyStorage, Long> {
}
