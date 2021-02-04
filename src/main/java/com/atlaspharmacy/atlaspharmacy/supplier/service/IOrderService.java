package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.List;

public interface IOrderService {
    List<Order> getSuppliersOrders(SupplierDTO supplierDTO);
    
}
