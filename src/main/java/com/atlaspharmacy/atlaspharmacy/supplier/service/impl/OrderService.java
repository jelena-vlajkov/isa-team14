package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OrderRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final MedicationInOrderService medicationInOrderService;
    public OrderService(OrderRepository orderRepository, MedicationInOrderService medicationInOrderService) {
        this.orderRepository = orderRepository;

        this.medicationInOrderService = medicationInOrderService;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public List<OrderDTO> getAllunfinishedOrders(){
        List<Order> unfinished = new ArrayList<>();
        Date currentDate = new Date();

        for(Order o: getAllOrders()) {
            if (currentDate.compareTo(o.getDueDate()) < 0) {
                unfinished.add(o);
            }
        }
        List<OrderDTO> dtos = new ArrayList<>();
        for(Order o : unfinished){
            OrderDTO dto = OrderMapper.mapOrderToDTO(o);
            List<OrderedMedication> orderedMedications = new ArrayList<>();
            for(MedicationInOrder m : medicationInOrderService.getAllMedicationsByOrder(o.getId())){
                orderedMedications.add(m.getOrderedMedication());
            }
            dto.setOrderedMedication(orderedMedications);
            dtos.add(dto);
        }
        return dtos;
    }
}
