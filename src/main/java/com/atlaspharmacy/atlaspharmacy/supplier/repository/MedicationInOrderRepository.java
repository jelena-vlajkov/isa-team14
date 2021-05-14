package com.atlaspharmacy.atlaspharmacy.supplier.repository;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationInOrderRepository extends JpaRepository<MedicationInOrder, Long> {
}
