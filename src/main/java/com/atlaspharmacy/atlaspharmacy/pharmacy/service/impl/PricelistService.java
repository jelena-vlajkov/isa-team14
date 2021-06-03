package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PricelistService implements IPricelistService {
    private final PricelistRepository pricelistRepository;
    private final PharmacyStorageService pharmacyStorageService;
    private final IPharmacyService pharmacyService;
    private final IMedicationService medicationService;

    @Autowired
    public PricelistService(PricelistRepository pricelistRepository, PharmacyStorageService pharmacyStorageService, IPharmacyService pharmacyService, IMedicationService medicationService) {
        this.pricelistRepository = pricelistRepository;
        this.pharmacyStorageService = pharmacyStorageService;
        this.pharmacyService = pharmacyService;
        this.medicationService = medicationService;
    }

    @Override
    public List<PricelistDTO> getPricelistsByMedication(Long code) {
        List<PricelistDTO> pricelists = new ArrayList<>();
        Date today = new Date();
        for(Pricelist p : pricelistRepository.getPricelistsByMedicationCode(code)){
            pricelists.add(PricelistMapper.mapPricelistToDTO(p));
        }
        return pricelists;
    }

    @Override
    public PricelistDTO getPricelistByMedicationAndPeriod(Long code, PeriodDTO period) {
        return PricelistMapper.mapPricelistToDTO(pricelistRepository.getPricelistsByMedicationCodeAndPeriod(code, period.getStartPeriod(), period.getEndPeriod()));
    }

    public boolean addMedicationToPricelist(PricelistDTO pricelistDTO) {
        if(getPricelistForMedicationAndPharmacyAndPeriod(pricelistDTO.getMedication().getCode(),pricelistDTO.getPharmacy().getId(),pricelistDTO.getStartPeriod(),pricelistDTO.getEndPeriod()) == null )
        {
            Pricelist newPricelist=new Pricelist();
            newPricelist.setPrice(pricelistDTO.getPrice());
            newPricelist.setPeriod(new Period(pricelistDTO.getStartPeriod(),pricelistDTO.getEndPeriod()));
            newPricelist.setPharmacy(PharmacyMapper.mapDTOToPharmacy(pricelistDTO.getPharmacy()));
            Medication medication = MedicationMapper.convertToMedication(medicationService.findById(pricelistDTO.getMedication().getId()));
            newPricelist.setMedication(medication);
            pricelistRepository.save(newPricelist);
            pharmacyStorageService.addMedicationToPharmacy(medication.getId(),pricelistDTO.getPharmacy().getId(),0L);
            return true;
        }

        return false;
    }

    @Transactional
    public void editPricelist(PricelistDTO pricelistDTO) throws Exception {
        Pricelist pricelistToUpdate = pricelistRepository.getOne(pricelistDTO.getId());
        pricelistToUpdate.setPrice(pricelistDTO.getPrice());
        pricelistToUpdate.setPeriod(new Period(pricelistDTO.getStartPeriod(),pricelistDTO.getEndPeriod()));
        pricelistRepository.save(pricelistToUpdate);
    }

    @Override
    public Pricelist getPricelistForMedicationAndPharmacy(Long code, Long pharmacyId) {
        return pricelistRepository.getPricelistsByMedicationCodeAndPharmacy(code, pharmacyId);
    }

    @Transactional
    public void deletePricelistEntity(Long pricelistId) {
        Pricelist pricelistToDelete=pricelistRepository.findById(pricelistId).get();
        pricelistRepository.delete(pricelistToDelete);
    }

    @Override
    public Pricelist getPricelistForMedicationAndPharmacyAndPeriod(Long code, Long pharmacyId,Date startDate,Date endDate) {
        return pricelistRepository.getPricelistsByMedicationCodeAndPharmacyAndPeriod(code,pharmacyId,startDate,endDate);
    }

    @Override
    public List<Pricelist> getPricelistsByPharmacy(Long pharmacyId) {
        return pricelistRepository.getPricelistsByPharmacy(pharmacyId);
    }


}
