package com.atlaspharmacy.atlaspharmacy.promotions.repository;

import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {
}
