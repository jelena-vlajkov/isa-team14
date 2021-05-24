package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyPricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyPricelistRepository extends JpaRepository<PharmacyPricelist, Long> {

}
