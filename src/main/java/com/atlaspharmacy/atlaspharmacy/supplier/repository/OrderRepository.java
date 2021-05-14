package com.atlaspharmacy.atlaspharmacy.supplier.repository;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
