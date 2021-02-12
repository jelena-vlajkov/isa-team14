package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;

import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
}
