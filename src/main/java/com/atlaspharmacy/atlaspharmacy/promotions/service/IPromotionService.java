package com.atlaspharmacy.atlaspharmacy.promotions.service;

import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IPromotionService {
    public Promotion addPromotion(PromotionDTO promotionDTO) throws IOException, MessagingException;
    public List<Promotion> getPromotionsByPharmacy(Long pharmacyId);
}
