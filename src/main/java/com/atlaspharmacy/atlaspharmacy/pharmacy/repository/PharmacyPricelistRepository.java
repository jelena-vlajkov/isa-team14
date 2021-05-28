package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyPricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PricelistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyPricelistRepository extends JpaRepository<PharmacyPricelist, Long> {

    @Query(value = "SELECT p FROM PharmacyPricelist p WHERE p.pharmacy.id = ?1 AND p.startDate < current_date AND p.endDate > current_date AND p.type = ?2")
    PharmacyPricelist findPricelistByPharmacyAndType(Long pharmacyId, String type);

}
