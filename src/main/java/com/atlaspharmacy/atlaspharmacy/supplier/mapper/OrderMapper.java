package com.atlaspharmacy.atlaspharmacy.supplier.mapper;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private OrderMapper(){}
//    public static OrderDTO mapOrderToDTO(Order order){
//        return new OrderDTO(order.getId(), order.getMedicationOrder(), order.getDueDate(), SupplierMapper.mapSupplierToDTO(order.getSupplier()));
//    }
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
