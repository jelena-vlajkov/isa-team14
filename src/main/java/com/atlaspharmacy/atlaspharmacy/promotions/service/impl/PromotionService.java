package com.atlaspharmacy.atlaspharmacy.promotions.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.promotions.DTO.PromotionDTO;
import com.atlaspharmacy.atlaspharmacy.promotions.domain.Promotion;
import com.atlaspharmacy.atlaspharmacy.promotions.repository.PromotionRepository;
import com.atlaspharmacy.atlaspharmacy.promotions.service.IPromotionService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import com.atlaspharmacy.atlaspharmacy.users.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    private final PromotionRepository promotionRepository;
    private final ISubscriptionService subscriptionService;
    private final IEmailService emailService;
    private final IPharmacyService pharmacyService;



    @Autowired
    public PromotionService(PromotionRepository promotionRepository, ISubscriptionService subscriptionService, IEmailService emailService, IEmailService emailService1, IPharmacyService pharmacyService) {
        this.promotionRepository = promotionRepository;
        this.subscriptionService = subscriptionService;
        this.emailService = emailService1;
        this.pharmacyService = pharmacyService;
    }

    @Override
    public Promotion addPromotion(PromotionDTO promotionDTO) throws IOException, MessagingException {
        Promotion p=new Promotion();
        Period period=new Period();
        period.setStartTime(promotionDTO.getStartTime());
        period.setEndTime(promotionDTO.getEndTime());
        p.setActivePeriod(period);
        p.setDescription(promotionDTO.getDescription());
        p.setPharmacy(pharmacyService.getById(promotionDTO.getPharmacy().getId()));
        promotionRepository.save(p);
       List<Patient> subscribedUsers=subscriptionService.getAllSubscribedForPharmacy(promotionDTO.getPharmacy().getId());
        for(Patient subscribedUser:subscribedUsers){
            emailService.sendPromotionNotification(subscribedUser,p);
        }
        return p;
    }

    @Override
    public List<Promotion> getPromotionsByPharmacy(Long pharmacyId) {
        List<Promotion> promotionsForPharmacy = new ArrayList<>();
        Date today = new Date();
        for(Promotion p : promotionRepository.findAll()){
            if(today.compareTo(p.getActivePeriod().getEndTime())<0 && today.compareTo(p.getActivePeriod().getStartTime())>0){
                    promotionsForPharmacy.add(p);
            }
        }
        return promotionsForPharmacy;
    }
}
