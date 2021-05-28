package com.atlaspharmacy.atlaspharmacy.promotions.repository;

import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
    @Query(value = "SELECT p FROM Promotion p WHERE p.pharmacy.id = ?1 AND CAST(p.activePeriod.startTime as date) > current_date AND CAST(p.activePeriod.endTime as date) < current_date")
    List<Promotion> getPromotionBy(Long pharmacyId);
}
