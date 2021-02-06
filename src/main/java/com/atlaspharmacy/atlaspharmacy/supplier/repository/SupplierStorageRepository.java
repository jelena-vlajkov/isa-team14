package com.atlaspharmacy.atlaspharmacy.supplier.repository;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;
import com.atlaspharmacy.atlaspharmacy.supplier.service.impl.SupplierStorageService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierStorageRepository extends JpaRepository<SupplierStorageMedication, Long> {
}
