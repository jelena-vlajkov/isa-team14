package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPharmacistRepository extends JpaRepository<Pharmacist,Long> {
}
