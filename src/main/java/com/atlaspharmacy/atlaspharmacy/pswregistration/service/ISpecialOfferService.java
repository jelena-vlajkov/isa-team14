package com.atlaspharmacy.atlaspharmacy.pswregistration.service;

import java.util.List;

import com.atlaspharmacy.atlaspharmacy.pswregistration.model.SpecialOffer;

public interface ISpecialOfferService {
    public boolean add(SpecialOffer offer);
    public boolean remove(int id);
    public boolean update(SpecialOffer offer);
    public SpecialOffer get(int id);
    public List<SpecialOffer> getAll();
}