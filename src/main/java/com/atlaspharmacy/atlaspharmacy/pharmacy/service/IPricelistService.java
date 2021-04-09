package com.atlaspharmacy.atlaspharmacy.pharmacy.service;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;

import java.util.List;

public interface IPricelistService {
    List<PricelistDTO> getPricelistsByMedication(Long code);
    PricelistDTO getPricelistByMedicationAndPeriod(Long code, PeriodDTO periodDTO);
}
