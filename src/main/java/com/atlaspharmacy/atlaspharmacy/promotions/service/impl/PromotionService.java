package com.atlaspharmacy.atlaspharmacy.promotions.service.impl;

import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.promotions.mapper.PromotionMapper;
import com.atlaspharmacy.atlaspharmacy.promotions.repository.PromotionRepository;
import com.atlaspharmacy.atlaspharmacy.promotions.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService implements IPromotionService {
    private final PromotionRepository _promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        _promotionRepository = promotionRepository;
    }

    @Override
    public Promotion addPromotion(PromotionDTO promotionDTO) {
        Promotion p= PromotionMapper.MapDTOToPromotion(promotionDTO);
        _promotionRepository.save(p);
        return p;
    }
}
