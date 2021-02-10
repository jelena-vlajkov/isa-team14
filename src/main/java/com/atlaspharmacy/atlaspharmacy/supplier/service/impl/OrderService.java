package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OfferMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OfferRepository;
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
    private final MedicationServiceImpl medicationService;
    private final OfferRepository offerRepository;
    public OrderService(OrderRepository orderRepository, MedicationInOrderService medicationInOrderService, MedicationServiceImpl medicationService, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.medicationInOrderService = medicationInOrderService;
        this.medicationService = medicationService;
        this.offerRepository = offerRepository;
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
            List<OrderedMedicationDTO> orderedMedications = new ArrayList<>();
            for(MedicationInOrder m : medicationInOrderService.getAllMedicationsByOrder(o.getId())){
                //orderedMedications.add(m.getOrderedMedication());
                MedicationDTO mdto = MedicationMapper.convertToMedicationDTO(medicationService.getById(m.getOrderedMedication().getMedication()));
                OrderedMedicationDTO odto = new OrderedMedicationDTO();
                odto.setMedication(mdto);
                odto.setQuantity(m.getOrderedMedication().getQuantity());
                orderedMedications.add(odto);
            }

            dto.setOrderedMedication(orderedMedications);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public OrderDTO getByIdentifier(int uniqueidentifier) {
        for(OrderDTO dto: getAllunfinishedOrders()){
            if(dto.getUniqueidentifier()==uniqueidentifier){
                return dto;
            }
        }
        return null;
    }

    @Override
    public Order getByUniqueIdentifier(int uniqueidentifier) {
        for(Order o: getAllOrders()){
            if(o.getUniqueidentifier()==uniqueidentifier){
                return o;
            }
        }
        return null;
    }

//    @Override
//    public List<OrderDTO> getAllOrdersWehereOfferIsNotGivenBySupplier(Long id) {
//        List<Offer> offers = offerRepository.findAll();
//        List<Offer> suppliersOffers = new ArrayList<>();
//
//        for(Offer o : offers){
//            if(o.getSupplier().getId().equals(id)){
//                suppliersOffers.add(o);
//            }
//        }
//
//        List<OrderDTO> dtos = new ArrayList<>();
//        for(Offer o : suppliersOffers){
//            if(!offerPlaced(o)){
//                dtos.add(OrderMapper.mapOrderToDTO(orderRepository.findById(o.getOrder().getId()).get()));
//            }
//        }
//
//
//        return dtos;
//    }
//    private boolean offerPlaced(Offer o){
//        for(OrderDTO dto : getAllunfinishedOrders()){
//            if(o.getOrder().getUniqueidentifier()==dto.getUniqueidentifier()){
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public List<OrderDTO> getAllOrdersWehereOfferIsNotGivenBySupplier(Long id) {
        List<Order> placedOrders = new ArrayList<>();
        for(Offer o : offerRepository.findAll()){
            if(o.getSupplier().getId().equals(id)){
                placedOrders.add(o.getOrder());
            }
        }
        int idkanymore = 0;
        List<OrderDTO> notPlacedOffers = new ArrayList<>();
        for(OrderDTO o : getAllunfinishedOrders()){
            idkanymore=0;
            for(Order order : placedOrders){
                if(o.getId().equals(order.getId())){
                    idkanymore=1;
                }
            }
            if(idkanymore==0){
                notPlacedOffers.add(o);
            }
        }
        return notPlacedOffers;
    }


//    @Override
//    public List<OrderDTO> getAllOrdersWehereOfferIsNotGivenBySupplier(Long id) {
//        List<Offer> offers = offerRepository.findAll();
//        List<OfferDTO> suppliersOffers = new ArrayList<>();
//        for(Offer o : offers){
//            if(o.getSupplier().getId().equals(id)){
//                suppliersOffers.add(OfferMapper.mapOfferToDTO(o));
//            }
//        }
//
//        List<OrderDTO> suppliersNotGivenOffer = new ArrayList<>();
//        for(OfferDTO offerDTO : suppliersOffers){
//            if(!checkOrderInList(offerDTO.getOrder().getId())){
//                suppliersNotGivenOffer.add(offerDTO.getOrder());
//            }
//        }
//        return suppliersNotGivenOffer;
//    }

    public boolean checkOrderInList(Long id){
        for(OrderDTO o : getAllunfinishedOrders()){
            if(o.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<OrderedMedicationDTO> getOrderedMedicationByIdentifier(int id) {
        return getByIdentifier(id).getOrderedMedication();
    }

}
