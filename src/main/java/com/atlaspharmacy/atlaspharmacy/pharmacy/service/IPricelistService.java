package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;

import java.util.List;

public interface IPricelistService {
    List<PricelistDTO> getPricelistsByMedication(Long code);
    List<PricelistDTO> getByPharmacy(Long id);
}
