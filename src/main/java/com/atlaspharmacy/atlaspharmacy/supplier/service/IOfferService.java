package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OfferDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;

import java.util.List;

public interface IOfferService {
    List<OfferDTO> getOffersBySupplier(Long id);
    List<Offer> getAllOffersByPharmacy(Long id);
    List<Offer> getAllOffers();
    Offer editOffer(OfferDTO offer) throws DueDateSoonException;
    Offer giveOffer(OfferDTO o) throws DueDateSoonException, InsuficientFundsException;
    List<OfferDTO> getUsersOffersByStatus(Long status, Long supplier);
    List<OfferDTO> getAllOfersForOrder(Long orderId);
}
