package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    @Query(value = "SELECT p FROM Pharmacist p WHERE p.pharmacy.id = ?1")
    List<Pharmacist> findPharmacistByPharmacy(Long pharmacyId);
}
