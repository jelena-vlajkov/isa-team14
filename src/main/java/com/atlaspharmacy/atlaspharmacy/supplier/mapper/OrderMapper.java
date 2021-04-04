package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private OrderMapper(){}
    public static OrderDTO mapOrderToDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUniqueidentifier(order.getUniqueidentifier());
        orderDTO.setId(order.getId());
        orderDTO.setDueDate(order.getDueDate());
        orderDTO.setPharmacy(PharmacyMapper.mapPharmacyToDTO(order.getPharmacy()));
        List<OrderedMedicationDTO> orderedMedications = new ArrayList<>();

        orderDTO.setOrderedMedication(orderedMedications);
        return orderDTO;

    }
    public static List<OrderDTO> mapToListDTOS(List<Order> orders){
        List<OrderDTO> dtos = new ArrayList<>();
        for(Order o : orders)
        {
            dtos.add(OrderMapper.mapOrderToDTO(o));
        }
        return dtos;
    }
//
//    public static Order mapDTOToOrder(OrderDTO dto){
//        Order o = new Order();
//        o.setId(dto.getId());
//        o.setMedicationOrder(dto.getMedicationOrder());
//        o.setDueDate(dto.getDueDate());
//        o.setSupplier(SupplierMapper.mapDTOToSupplier(dto.getSupplier()));
//        return o;
//    }
//    public static List<OrderDTO> mapToListDTOS(List<Order> orders){
//        List<OrderDTO> dtos = new ArrayList<>();
//        for(Order o : orders){
//            dtos.add(OrderMapper.mapOrderToDTO(o));
//        }
//        return dtos;
//    }


}
