package com.atlaspharmacy.atlaspharmacy.promotions.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.promotions.mapper.PromotionMapper;
import com.atlaspharmacy.atlaspharmacy.promotions.repository.PromotionRepository;
import com.atlaspharmacy.atlaspharmacy.promotions.service.IPromotionService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    private final PromotionRepository promotionRepository;
    private final ISubscriptionService subscriptionService;
    private final IEmailService emailService;


    @Autowired
    public PromotionService(PromotionRepository promotionRepository, ISubscriptionService subscriptionService, IEmailService emailService, IEmailService emailService1) {
        this.promotionRepository = promotionRepository;
        this.subscriptionService = subscriptionService;
        this.emailService = emailService1;
    }

    @Override
    public Promotion addPromotion(PromotionDTO promotionDTO) throws IOException, MessagingException {
        Promotion p= PromotionMapper.MapDTOToPromotion(promotionDTO);
        promotionRepository.save(p);
        List<Patient> subscribedUsers=subscriptionService.getAllSubscribedForPharmacy(promotionDTO.getPharmacy().getId());
        for(Patient subscribedUser:subscribedUsers){
            emailService.sendPromotionNotification(subscribedUser,p);
        }
        return p;
    }
}
