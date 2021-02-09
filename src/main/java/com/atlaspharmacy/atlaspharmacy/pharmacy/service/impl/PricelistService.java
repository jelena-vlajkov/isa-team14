package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PricelistService implements IPricelistService {
    private final PricelistRepository pricelistRepository;
    private final PharmacyStorageService pharmacyStorageService;
    @Autowired
    public PricelistService(PricelistRepository pricelistRepository, PharmacyStorageService pharmacyStorageService) {
        this.pricelistRepository = pricelistRepository;
        this.pharmacyStorageService = pharmacyStorageService;
    }

    @Override
    public List<PricelistDTO> getPricelistsByMedication(Long code) {
        List<PricelistDTO> pricelists = new ArrayList<>();
        Date today = new Date();
        for(Pricelist p : pricelistRepository.findAll()){
            if(p.getMedication().getCode().equals(code) && today.compareTo(p.getPeriod().getEndTime())<0 && today.compareTo(p.getPeriod().getStartTime())>0){
                if(pharmacyStorageService.isMedicationInPharmacy(p.getMedication().getCode(), p.getPharmacy().getId())){
                    pricelists.add(PricelistMapper.mapPricelistToDTO(p));
                }
            }
        }
        return pricelists;
    }
}
