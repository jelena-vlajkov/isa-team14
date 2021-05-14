package com.atlaspharmacy.atlaspharmacy.promotions.service;

import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IPromotionService {

    public Promotion addPromotion(PromotionDTO promotionDTO) throws IOException, MessagingException;
}
