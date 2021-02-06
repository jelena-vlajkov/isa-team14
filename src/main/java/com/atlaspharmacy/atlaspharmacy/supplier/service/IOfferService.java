package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.reservations.exception.DueDateSoonException;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Offer;
import com.atlaspharmacy.atlaspharmacy.supplier.exceptions.InsuficientFundsException;

import java.util.List;

public interface IOfferService {
    List<Offer> getOffersBySupplier(Long id);
    List<Offer> getAllOffersByPharmacy(Long id);
    List<Offer> getAllOffers();
    Offer editOffer(Offer offer) throws DueDateSoonException;
    Offer giveOffer(Offer o) throws DueDateSoonException, InsuficientFundsException;
}
