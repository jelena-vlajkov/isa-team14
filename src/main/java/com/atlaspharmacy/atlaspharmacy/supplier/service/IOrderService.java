package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.MedicationOrderStatus;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;

import java.util.List;

public interface IOrderService {
    List<Order> getSuppliersOrders(SupplierDTO supplierDTO);

    Order addOrder(OrderDTO orderDTO);
    List<Order> getAllOrders();
    List<OrderDTO> getAllunfinishedOrders();
    OrderDTO getByIdentifier(int uniqueidentifier);
    Order getByUniqueIdentifier(int uniqueidentifier);
    List<OrderDTO> getAllOrdersWhereOfferIsNotGivenBySupplier(Long id);
    List<OrderedMedicationDTO> getOrderedMedicationByIdentifier(int id);
    List<OrderDTO> getAllOrdersByPharmacy(Long pharmacyId);
    OrderDTO findById(Long orderId);
    List<OrderDTO> filterOrdersByState(String status);
    void changeOrderStatus(Long orderId, MedicationOrderStatus status);
}
