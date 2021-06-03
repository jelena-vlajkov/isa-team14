package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.service.implementations.MedicationServiceImpl;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.MedicationOrderStatus;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.enums.OfferStatus;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.OfferMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OfferRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOfferService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.EmailService;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements IOfferService {
    private final OfferRepository offerRepository;
    private final SupplierStorageService supplierStorageService;
    private final MedicationInOrderService medicationInOrderService;
    private final MedicationServiceImpl medicationService;
    private final OrderService orderService;
    private final SupplierService supplierService;
    private final EmailService emailService;
    private final IPharmacyStorageService pharmacyStorageService;

    @Autowired
    public OfferService(OfferRepository offerRepository, SupplierStorageService supplierStorageService,
                        MedicationInOrderService medicationInOrderService,
                        MedicationServiceImpl medicationService, OrderService orderService,
                        SupplierService supplierService, EmailService emailService, IPharmacyStorageService pharmacyStorageService) {
        this.offerRepository = offerRepository;
        this.supplierStorageService = supplierStorageService;
        this.medicationInOrderService = medicationInOrderService;
        this.medicationService = medicationService;
        this.orderService = orderService;
        this.supplierService = supplierService;
        this.emailService = emailService;
        this.pharmacyStorageService = pharmacyStorageService;
    }

    @Override
    public List<OfferDTO> getOffersBySupplier(Long id) {
        List<Offer> allOffers = getAllOffers();
        List<OfferDTO> suppliersOffers = new ArrayList<>();
        for (Offer o : allOffers) {
            if (o.getSupplier().getId().equals(id)) {
                OfferDTO dto = OfferMapper.mapOfferToDTO(o);
                OrderDTO orderDTO = dto.getOrder();
                //  List<OrderedMedicationDTO> kasfbaf = setMedicationDTOStoOrderDTO(o);
                //orderDTO.setOrderedMedication(kasfbaf);
                dto.setOrder(orderDTO);
                suppliersOffers.add(dto);
            }
        }

        return suppliersOffers;

    }

    private List<OrderedMedicationDTO> setMedicationDTOStoOrderDTO(Offer offer) {
        List<OrderedMedicationDTO> medicationDTOS = new ArrayList<>();
        for (MedicationInOrder m : medicationInOrderService.getAllMedicationsByOrder(offer.getOrder().getId())) {
            OrderedMedicationDTO dto = new OrderedMedicationDTO();
            dto.setMedicationId(m.getOrderedMedication().getMedication());
            dto.setMedicationName(medicationService.findById(m.getOrderedMedication().getMedication()).getName());
            dto.setQuantity(m.getOrderedMedication().getQuantity());
            medicationDTOS.add(dto);
        }
        return medicationDTOS;
    }

    @Override
    public List<Offer> getAllOffersByPharmacy(Long id) {
        List<Offer> allOffers = getAllOffers();
        List<Offer> pharmacyOffers = new ArrayList<>();
        for (Offer o : allOffers) {
            if (o.getOrder().getPharmacy().getId().equals(id)) {
                pharmacyOffers.add(o);
            }
        }

        return pharmacyOffers;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public boolean checkAvailableMedication(OfferDTO offer) {
        List<OrderedMedicationDTO> ordersMedication = orderService.getOrderedMedicationByIdentifier(offer.getOrder().getUniqueidentifier());

        for (OrderedMedicationDTO m : ordersMedication) {
            if (!supplierStorageService.medicationPresentInStorage(m, offer.getSupplier().getId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Offer giveOffer(OfferDTO offer) throws DueDateSoonException, InsuficientFundsException {
        if (checkAvailableMedication(offer)) {
            Date currentDate = new Date();
            if (currentDate.compareTo(offer.getOrder().getDueDate()) < 0) {
                Offer o = OfferMapper.mapDTOToOffer(offer);
                Order order = orderService.getByUniqueIdentifier(o.getOrder().getUniqueidentifier());
                o.setOrder(order);
                o.setSupplier(supplierService.findByEmail(o.getSupplier().getEmail()));
                return offerRepository.save(o);

            }
            throw new DueDateSoonException("Cant offer, due date too soon!");
        }
        throw new InsuficientFundsException("There arent enought medication avaliable");
    }

    @Override
    public List<OfferDTO> getUsersOffersByStatus(Long status, Long supplier) {
        List<OfferDTO> usersOffers = new ArrayList<>();
        for (OfferDTO dto : getOffersBySupplier(supplier)) {
            if (dto.getOfferStatus().ordinal() == status) {
                usersOffers.add(dto);
            }
        }
        return usersOffers;
    }

    @Override
    public List<OfferDTO> getAllOfersForOrder(Long orderId) {
        List<Offer> allOffers = offerRepository.findAll();
        List<OfferDTO> offersForOrder = new ArrayList();
        for (Offer o : allOffers) {
            if (o.getOrder().getId().equals(orderId) && o.getOfferStatus().equals(OfferStatus.PENDING)) {
                OfferDTO dto = OfferMapper.mapOfferToDTO(o);
                List<OrderedMedicationDTO> omDTO = setMedicationDTOStoOrderDTO(o);
                dto.getOrder().setOrderedMedications(omDTO);
                offersForOrder.add(dto);
            }
        }
        return offersForOrder;
    }

    public Offer getOfferByIdentifier(int uniqueidentifier) {
        List<Offer> allOffers = getAllOffers();
        for (Offer o : allOffers) {
            if (o.getUniqueidentifier() == uniqueidentifier) {
                return o;
            }
        }
        return null;
    }

    @Override
    public Offer editOffer(OfferDTO offer) throws DueDateSoonException {
        Date currentDate = new Date();
        if (currentDate.compareTo(offer.getOrder().getDueDate()) < 0) {
            Offer editedOffer = getOfferByIdentifier(offer.getUniqueidentifier());
            editedOffer.setPrice(offer.getPrice());
            editedOffer.setDueDelivery(offer.getDueDelivery());
            return offerRepository.save(editedOffer);
        }
        throw new DueDateSoonException("Cant edit offer, due date too soon!");
    }


    @Override
    @Transactional
    public List<Offer> chooseOffer(OfferDTO offerDTO) throws IOException, MessagingException {
        orderService.changeOrderStatus(offerDTO.getOrder().getId(), MedicationOrderStatus.PROCESSED);
        List<Offer> allOffersByOrder = getAllOffers().stream()
                .filter(offer -> offer.getOrder().getId()
                        .equals(offerDTO.getOrder().getId()))
                .collect(Collectors.toList());

        for (Offer o : allOffersByOrder) {
            if (o.getId().equals(offerDTO.getId())) {
                o.setOfferStatus(OfferStatus.ACCEPTED);
                offerRepository.save(o);
                emailService.sendNotificationToSupplier(o.getSupplier(), true);
                List<MedicationInOrder> medicationsByOrder=medicationInOrderService.getAllMedicationsByOrder(o.getOrder().getId());
                for(MedicationInOrder m:medicationsByOrder) {
                    pharmacyStorageService.addMedicationToPharmacy(m.getOrderedMedication().getMedication(),o.getOrder().getPharmacy().getId(), 0L);
                }
                } else {
                o.setOfferStatus(OfferStatus.REJECTED);
                offerRepository.save(o);
                emailService.sendNotificationToSupplier(o.getSupplier(), false);
            }
        }
        return allOffersByOrder;
    }
}