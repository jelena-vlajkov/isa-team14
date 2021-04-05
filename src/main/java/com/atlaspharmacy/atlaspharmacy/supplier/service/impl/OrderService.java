package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.OrderedMedication;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OfferMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OrderedMedicationMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.MedicationInOrderRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OfferRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OrderRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOrderService;
import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import org.springframework.security.core.parameters.P;
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
    private final MedicationInOrderRepository medicationInOrderRepository;

    public OrderService(OrderRepository orderRepository,
                        MedicationInOrderService medicationInOrderService,
                        MedicationServiceImpl medicationService,
                        OfferRepository offerRepository,
                        MedicationInOrderRepository medicationInOrderRepository) {
        this.orderRepository = orderRepository;
        this.medicationInOrderService = medicationInOrderService;
        this.medicationService = medicationService;
        this.offerRepository = offerRepository;
        this.medicationInOrderRepository=medicationInOrderRepository;
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
                odto.setMedicationId(mdto.getId());
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
    public List<Order> getSuppliersOrders(SupplierDTO supplierDTO) {
        return null;
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        Order order=new Order();
        order.setDueDate(orderDTO.getDueDate());
        order.setPharmacy(PharmacyMapper.mapDTOToPharmacy(orderDTO.getPharmacy()));
        orderRepository.save(order);

        for(OrderedMedicationDTO o: orderDTO.getOrderedMedications())
        {
            MedicationInOrder medicationInOrder=new MedicationInOrder();
            medicationInOrder.setOrder(order);
            Medication medication=medicationService.getById(o.getMedicationId());
            o.setMedicationId(medication.getId());
            medicationInOrder.setOrderedMedication(OrderedMedicationMapper.mapDTOtoMedication(o));
            medicationInOrderRepository.save(medicationInOrder);
        }
        return order;
    }
    public Order getByUniqueIdentifier(int uniqueidentifier) {
        for(Order o: getAllOrders()){
            if(o.getUniqueidentifier()==uniqueidentifier){
                return o;
            }
        }
        return null;
    }


    @Override
    public List<OrderDTO> getAllOrdersWhereOfferIsNotGivenBySupplier(Long id) {
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


    @Override
    public List<OrderedMedicationDTO> getOrderedMedicationByIdentifier(int id) {
        return getByIdentifier(id).getOrderedMedication();
    }

    @Override
    public List<OrderDTO> filterOrdersByState(String status) {
        List<Order> allOrders=orderRepository.findAll();
        List<Order> filteredOrders=new ArrayList<>();
        for(Order o:allOrders) {
            if(o.getStatus().equals(status)){
                filteredOrders.add(o);
            }
        }
        return OrderMapper.mapToListDTOS(filteredOrders);
    }


}
