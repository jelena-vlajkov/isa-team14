package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PricelistMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PricelistRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for(Pricelist p : pricelistRepository.findAll()){
            if(p.getMedication().getCode().equals(code) && today.compareTo(p.getPeriod().getEndTime())<0 && today.compareTo(p.getPeriod().getStartTime())>0){
                if(pharmacyStorageService.isMedicationInPharmacy(p.getMedication().getCode(), p.getPharmacy().getId())){
                    pricelists.add(PricelistMapper.mapPricelistToDTO(p));
                }
            }
        }
        return pricelists;
    }

    @Override
    public PricelistDTO getPricelistByMedicationAndPeriod(Long code, PeriodDTO period) {
        List<PricelistDTO> pricelistsForMedication=getPricelistsByMedication(code);
        for(PricelistDTO p:pricelistsForMedication){
            if(p.getStartPeriod().before(period.getStartPeriod()))
                    if( p.getEndPeriod().after(period.getEndPeriod())){
                return p;
            }
        }
        return null;
    }
    public Pricelist addMedicationToPricelist(PricelistDTO pricelistDTO) {
        Pricelist newPricelist=new Pricelist();
        newPricelist.setPrice(pricelistDTO.getPrice());
        newPricelist.setPharmacy(PharmacyMapper.mapDTOToPharmacy(pricelistDTO.getPharmacy()));
        newPricelist.setMedication(MedicationMapper.convertToMedication(pricelistDTO.getMedication()));
        pricelistRepository.save(newPricelist);
        return newPricelist;
    }

    @Override
    public Pricelist editPricelistEntity(PricelistDTO pricelistDTO) throws Exception {
        Pricelist updatedPricelist=pricelistRepository.getOne(pricelistDTO.getId());
        updatedPricelist.setPrice(pricelistDTO.getPrice());
        Pharmacy updatedPharmacy=pharmacyService.editPharmacy(pricelistDTO.getPharmacy());
        updatedPricelist.setPharmacy(updatedPharmacy);
        Medication updatedMedication=medicationService.modifyMedication(pricelistDTO.getMedication().getId(),pricelistDTO.getMedication());
        updatedPricelist.setMedication(updatedMedication);
        pricelistRepository.save(updatedPricelist);
        return updatedPricelist;
    }

}
