package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;


import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Optional<Pharmacy> getById(Long id);
}
