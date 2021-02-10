package com.atlaspharmacy.atlaspharmacy.promotions.service;

import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;

public interface IPromotionService {

    public Promotion addPromotion(PromotionDTO promotionDTO);
}
