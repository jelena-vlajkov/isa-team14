package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.generalities.domain.Address;
import com.atlaspharmacy.atlaspharmacy.generalities.mapper.AddressMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {


    @Override
    public List<Order> getSuppliersOrders(SupplierDTO supplierDTO) {
        return null;
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        return null;
    }
}
