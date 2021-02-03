package com.atlaspharmacy.atlaspharmacy.users.repository;

import com.atlaspharmacy.atlaspharmacy.users.domain.SupplierAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAuthorityRepository extends JpaRepository<com.atlaspharmacy.atlaspharmacy.users.domain.SupplierAuthority, Long> {
}
