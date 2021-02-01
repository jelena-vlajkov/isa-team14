package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.PharmacyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPharmacyAdminRepository extends JpaRepository<PharmacyAdmin,Long> {
}
