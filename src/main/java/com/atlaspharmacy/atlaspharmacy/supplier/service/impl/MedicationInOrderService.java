package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.MedicationInOrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderedMedicationMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.MedicationInOrderRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OrderRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IMedicationInOrderService;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationInOrderService implements IMedicationInOrderService {
    private final MedicationInOrderRepository medicationInOrderRepository;
    private final OrderRepository orderRepository;

    public MedicationInOrderService(MedicationInOrderRepository medicationInOrderRepository
            , OrderRepository orderRepository) {
        this.medicationInOrderRepository = medicationInOrderRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<MedicationInOrder> getAllMedicationsByOrder(Long id) {
        Order order = orderRepository.findById(id).get();
        List<MedicationInOrder> ordersMedications = new ArrayList<>();
        List<MedicationInOrder> allmedicationsinallorders = medicationInOrderRepository.findAll();
        for(MedicationInOrder m : allmedicationsinallorders){
            if(m.getOrder().getId().equals(order.getId())){
                ordersMedications.add(m);
            }
        }
        return ordersMedications;
    }

    @Override
    public List<MedicationInOrder> getAllMedicationsByOrderIdenitfier(Long id) {
        Order order = orderRepository.findById(id).get();
        List<MedicationInOrder> ordersMedications = new ArrayList<>();
        List<MedicationInOrder> allmedicationsinallorders = medicationInOrderRepository.findAll();
        for(MedicationInOrder m : allmedicationsinallorders){
            if(m.getOrder().getId().equals(order.getId())){
                ordersMedications.add(m);
            }
        }
        return ordersMedications;
    }

    @Override
    public void addMedicationInOrder(List<MedicationInOrderDTO> dtos) {
        for(MedicationInOrderDTO medicationInOrderDTO:dtos) {
            MedicationInOrder medicationInOrder = new MedicationInOrder();
            medicationInOrder.setOrderedMedication(OrderedMedicationMapper.mapToOrderedMedication(medicationInOrderDTO.getOrderedMedication()));
            medicationInOrder.setOrder(orderRepository.findById(medicationInOrderDTO.getOrder().getId()).get());
            medicationInOrderRepository.save(medicationInOrder);
        }
    }

}
