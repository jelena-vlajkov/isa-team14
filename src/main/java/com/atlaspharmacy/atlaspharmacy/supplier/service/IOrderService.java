package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getSuppliersOrders(SupplierDTO supplierDTO);
    
}
