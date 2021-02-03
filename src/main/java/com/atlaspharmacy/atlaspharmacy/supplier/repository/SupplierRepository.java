package com.atlaspharmacy.atlaspharmacy.supplier.repository;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
