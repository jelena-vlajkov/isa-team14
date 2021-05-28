package com.atlaspharmacy.atlaspharmacy.membershipinfo.service.impl;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.SubscriptionDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.exceptions.AlreadySubscribedException;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.mapper.SubscriptionMapper;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.repository.SubscriptionRepository;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.service.ISubscriptionService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription subscribe(SubscriptionDTO dto) throws AlreadySubscribedException {
        for(Subscription s : subscriptionRepository.findAll()){
            if(s.getPatient().getEmail().equals(dto.getPatient().getEmail()) && s.getPharmacy().getId().equals(dto.getPharmacy().getId())){
                throw new AlreadySubscribedException();
            }
        }
        return subscriptionRepository.save(SubscriptionMapper.mapDTOToSubscription(dto));
    }

    @Override
    public List<Subscription> getAllUsersSubscriptions(Long userId) {
        return subscriptionRepository.getAllUsersSubscriptions(userId);
    }

    @Override
    public boolean unsubscribe(Long userId, Long pharmacyId) {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        for(Subscription s : subscriptions){
            if(s.getPatient().getId().equals(userId) && s.getPharmacy().getId().equals(pharmacyId)){
                subscriptionRepository.delete(s);
                return true;
            }
        }
        return false;

    }

    @Override
    public List<Patient> getAllSubscribedForPharmacy(Long pharmacyId) {
        List<Patient> subscribedUsers = new ArrayList<>();
        for(Subscription s : subscriptionRepository.getAllPharmacySubscriptions(pharmacyId)){
            subscribedUsers.add(s.getPatient());
        }
        return subscribedUsers;
    }

//    @Override
//    public boolean isSubscribed(Long userId, Long pharmacyId) {
//
//        for(Subscription s : subscriptionRepository.findAll()){
//            if(s.getPatient().getId().equals(userId) && s.getPharmacy().getId().equals(pharmacyId)){
//                return true;
//            }
//        }
//
//        return false;
//    }

}
