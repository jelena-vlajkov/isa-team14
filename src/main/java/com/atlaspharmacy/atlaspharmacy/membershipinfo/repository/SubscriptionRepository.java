package com.atlaspharmacy.atlaspharmacy.membershipinfo.repository;

import com.atlaspharmacy.atlaspharmacy.membershipinfo.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = "SELECT s FROM Subscription s WHERE s.patient.id = ?1")
    List<Subscription> getAllUsersSubscriptions(Long userId);

    @Query(value = "SELECT s FROM Subscription s WHERE s.pharmacy.id = ?1")
    List<Subscription> getAllPharmacySubscriptions(Long pharmacyId);



}
