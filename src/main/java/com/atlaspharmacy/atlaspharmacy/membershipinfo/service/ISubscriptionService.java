package com.atlaspharmacy.atlaspharmacy.membershipinfo.service;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO.SubscriptionDTO;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import com.atlaspharmacy.atlaspharmacy.membershipinfo.exceptions.AlreadySubscribedException;
import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import java.util.List;

public interface ISubscriptionService {
    Subscription subscribe(SubscriptionDTO dto) throws AlreadySubscribedException;
    List<Subscription> getAllUsersSubscriptions(Long userId);
    boolean unsubscribe(Long userId, Long pharmacyId);
    List<Patient> getAllSubscribedForPharmacy(Long pharmacyId);
}
