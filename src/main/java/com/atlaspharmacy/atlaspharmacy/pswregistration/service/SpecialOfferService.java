package com.atlaspharmacy.atlaspharmacy.pswregistration.service;

import java.util.List;

import com.atlaspharmacy.atlaspharmacy.pswregistration.model.SpecialOffer;
import com.atlaspharmacy.atlaspharmacy.pswregistration.repository.ISpecialOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService implements ISpecialOfferService {

    @Autowired
    private ISpecialOfferRepository repository;

    @Override
    public boolean add(SpecialOffer offer) {
        if(get(offer.getId()) != null)
            return false;
        repository.save(offer);
        return true;
    }

    @Override
    public boolean remove(int id) {
        SpecialOffer offer = get(id);
        if(offer == null)
            return false;
        repository.delete(offer);
        return true;
    }

    @Override
    public boolean update(SpecialOffer offer) {
        if(get(offer.getId()) == null)
            return false;
        repository.save(offer);
        return true;
    }

    @Override
    public SpecialOffer get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SpecialOffer> getAll() {
        return (List<SpecialOffer>)repository.findAll();
    }
    
}