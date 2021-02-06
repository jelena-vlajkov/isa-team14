package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.OfferRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfferService implements IOfferService {
    private final OfferRepository offerRepository;
    private final SupplierStorageService supplierStorageService;
    private final MedicationInOrderService medicationInOrderService;

    @Autowired
    public OfferService(OfferRepository offerRepository, SupplierStorageService supplierStorageService, MedicationInOrderService medicationInOrderService) {
        this.offerRepository = offerRepository;
        this.supplierStorageService = supplierStorageService;
        this.medicationInOrderService = medicationInOrderService;
    }

    @Override
    public List<Offer> getOffersBySupplier(Long id) {
        List<Offer> allOffers = getAllOffers();
        List<Offer> suppliersOffers = new ArrayList<>();
        for(Offer o: allOffers){
            if(o.getSupplier().getId().equals(id)){
                suppliersOffers.add(o);
            }
        }

        return suppliersOffers;

    }

    @Override
    public List<Offer> getAllOffersByPharmacy(Long id) {
        List<Offer> allOffers = getAllOffers();
        List<Offer> pharmacyOffers = new ArrayList<>();
        for(Offer o: allOffers){
            if(o.getOrder().getPharmacy().getId().equals(id)){
                pharmacyOffers.add(o);
            }
        }

        return pharmacyOffers;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public boolean checkAvailableMedication(Offer offer){
        List<MedicationInOrder> ordersMedication = medicationInOrderService.getAllMedicationsByOrder(offer.getOrder().getId());
        for(MedicationInOrder m : ordersMedication){
            if(!supplierStorageService.medicationPresentInStorage(m, offer.getSupplier().getId())){
                return false;
            }
        }
        return true;
    }

    @Override
    public Offer giveOffer(Offer offer) throws DueDateSoonException, InsuficientFundsException {
        if(checkAvailableMedication(offer)){
            Date currentDate = new Date();
            if(currentDate.compareTo(offer.getOrder().getDueDate())<0 && currentDate.compareTo(offer.getOrder().getEditableDue())<0){
                return offerRepository.save(offer);
            }
            throw new DueDateSoonException("Cant offer, due date too soon!");
        }
        throw new InsuficientFundsException("There arent enought medication avaliable");
    }

    public Offer getOfferByIdentifier(Offer offer){
        List<Offer> allOffers = getAllOffers();
        for(Offer o: allOffers){
            if(o.getUniqueidentifier()==offer.getUniqueidentifier()){
                return o;
            }
        }
        return null;
    }

    @Override
    public Offer editOffer(Offer offer) throws DueDateSoonException {
        Date currentDate = new Date();
        if(currentDate.compareTo(offer.getOrder().getEditableDue())<0){
            Offer editedOffer = getOfferByIdentifier(offer);
            editedOffer.setPrice(offer.getPrice());
            editedOffer.setDueDelivery(offer.getDueDelivery());
            return offerRepository.save(editedOffer);
        }
        throw new DueDateSoonException("Cant edit offer, due date too soon!");
    }
}
