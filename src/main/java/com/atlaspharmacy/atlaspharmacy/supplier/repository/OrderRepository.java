package com.atlaspharmacy.atlaspharmacy.supplier.repository;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
