package com.atlaspharmacy.atlaspharmacy.pharmacy.repository;


import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharamcy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPharmacyRepository extends CrudRepository<Pharamcy, Long> {
    Optional<Pharamcy> getById(Long id);
}
