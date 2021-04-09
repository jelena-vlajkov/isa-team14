package com.atlaspharmacy.atlaspharmacy.reports.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.DrugConsumptionDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PharmacyPeriodReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PharmacyIncomeReportDTO;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.PharmacyPeriodIncomeDTO;
import com.atlaspharmacy.atlaspharmacy.reports.service.IPharmacyBussinesReportService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyBussinesReportService implements IPharmacyBussinesReportService {
    private final IPricelistService pricelistService;
    private final IDrugReservationService drugReservationService;

    @Autowired
    public PharmacyBussinesReportService(IPricelistService pricelistService, IDrugReservationService drugReservationService) {
        this.pricelistService = pricelistService;
        this.drugReservationService = drugReservationService;
    }


    @Override
    public List<DrugConsumptionDTO> getDrugsConsumptionReportForPharmacyAndPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO) {
        List<DrugReservation> allDrugReservations=drugReservationService.findAllIssuedReservationsForPharmacyAndPeriod(pharmacyPeriodReportDTO.getPharmacyId(), pharmacyPeriodReportDTO.getPeriod());
        List<DrugConsumptionDTO> drugConsumptionsForPharmacy=new ArrayList<>();
        for(DrugReservation d:allDrugReservations) {
            if(!drugConsumptionsForPharmacy.stream().anyMatch(drugConsumption ->
                                                     drugConsumption.getMedication().getId().equals(d.getMedication().getId()))) {
                DrugConsumptionDTO newDrugConsumption=new DrugConsumptionDTO(MedicationMapper.convertToMedicationDTO(d.getMedication()),1);
                drugConsumptionsForPharmacy.add(newDrugConsumption);
            }
            else{
                DrugConsumptionDTO drugConsumptionDTO=drugConsumptionsForPharmacy.stream()
                                                      .filter(drugConsumption->
                                                              drugConsumption.getMedication().getId()
                                                                      .equals(d.getMedication().getId())).findFirst().get();
                drugConsumptionDTO.setNumberOfIssued(drugConsumptionDTO.getNumberOfIssued()+1);
            }
        }
        return drugConsumptionsForPharmacy;
    }

    @Override
    public PharmacyIncomeReportDTO getPharmacyIncomeReportForPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO) {
        List<DrugConsumptionDTO> allDrugConsumptionsForPeriod=getDrugsConsumptionReportForPharmacyAndPeriod(pharmacyPeriodReportDTO);
        double totalIncome=0;
        for(DrugConsumptionDTO drugConsumption:allDrugConsumptionsForPeriod) {
            PricelistDTO medicationPricelist=pricelistService.getPricelistByMedicationAndPeriod(drugConsumption.getMedication().getCode(), pharmacyPeriodReportDTO.getPeriod());
            totalIncome+=drugConsumption.getNumberOfIssued()*medicationPricelist.getPrice();
        }
        return new PharmacyIncomeReportDTO(pharmacyPeriodReportDTO.getPharmacyId(),totalIncome);

    }
}
