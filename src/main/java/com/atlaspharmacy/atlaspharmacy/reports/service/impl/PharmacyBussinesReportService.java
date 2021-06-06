package com.atlaspharmacy.atlaspharmacy.reports.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PricelistDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPricelistService;
import com.atlaspharmacy.atlaspharmacy.reports.DTO.*;
import com.atlaspharmacy.atlaspharmacy.reports.service.IPharmacyBussinesReportService;
import com.atlaspharmacy.atlaspharmacy.reservations.domain.DrugReservation;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public Long getPharmacyIncomeReportForPeriod(PharmacyPeriodReportDTO pharmacyPeriodReportDTO) {
        List<DrugConsumptionDTO> allDrugConsumptionsForPeriod=getDrugsConsumptionReportForPharmacyAndPeriod(pharmacyPeriodReportDTO);
        Long totalIncome=0L;
        for(DrugConsumptionDTO drugConsumption:allDrugConsumptionsForPeriod) {
            Pricelist medicationPricelist=pricelistService.getPricelistForMedicationAndPharmacyAndPeriod(drugConsumption.getMedication().getCode(),pharmacyPeriodReportDTO.getPharmacyId(), pharmacyPeriodReportDTO.getPeriod().getStartPeriod(), pharmacyPeriodReportDTO.getPeriod().getEndPeriod());
            totalIncome+=drugConsumption.getNumberOfIssued()*medicationPricelist.getPrice();
        }
        return totalIncome;

    }


    @Override
    public Long getDrugConsumptionsForMonth(int month, int year, Long pharmacyId) {
        Date startDate = new Date(year-1900, month - 1, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date endDate = new Date(year-1900, month - 1, day);
        return drugReservationService.findAllIssuedReservationsForPharmacyAndPeriod(pharmacyId, new PeriodDTO(startDate,endDate)).stream().count();
    }

    @Override
    public Long getDrugConsumptionsForHalfYear(int part, int year, Long pharmacyId) {
        Long scheduledForHalfYear = 0L;
        for(int i=4*(part-1)+1;i<4*(part-1)+5;i++){
            scheduledForHalfYear +=getDrugConsumptionsForMonth(i,year,pharmacyId);
        }
        return scheduledForHalfYear;
    }

    @Override
    public Long getDrugConsumptionsForYear(int year, Long pharmacyId) {
        Long scheduledForYear = 0L;
        for(int i=1;i<4;i++){
            scheduledForYear += getDrugConsumptionsForHalfYear(i,year,pharmacyId);
        }
        return scheduledForYear;
    }

    @Override
    public Long getPharmacyIncomeForMonth(int month, int year, Long pharmacyId) {
        Date startDate = new Date(year-1900, month - 1, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date endDate = new Date(year-1900, month - 1, day);
        PharmacyPeriodReportDTO periodReport=new PharmacyPeriodReportDTO(pharmacyId,new PeriodDTO(startDate,endDate));
        return getPharmacyIncomeReportForPeriod(periodReport);
    }

    @Override
    public Long getPharmacyIncomeForHalfYear(int part, int year, Long pharmacyId) {
        Long incomeForHalfYear = 0L;
        for(int i=4*(part-1)+1;i<4*(part-1)+5;i++){
            incomeForHalfYear +=getPharmacyIncomeForMonth(i,year,pharmacyId);
        }
        return incomeForHalfYear;
    }

    @Override
    public Long getPharmacyIncomeForYear(int year, Long pharmacyId) {
        Long incomeForYear = 0L;
        for(int i=1;i<4;i++){
            incomeForYear += getPharmacyIncomeForHalfYear(i,year,pharmacyId);
        }
        return incomeForYear;
    }

    @Override
    public List<Long> getPharmacyIncomeReportByMonths(int year, Long pharmacyId) {
        List<Long> pharmacyIncomes=new ArrayList<>();
        for(int i=1;i<=12;i++){
            pharmacyIncomes.add(getPharmacyIncomeForMonth(i,year,pharmacyId));
        }
        return pharmacyIncomes;
    }

    @Override
    public List<Long> getPharmacyIncomeReportByHalfYears(int year, Long pharmacyId) {
        List<Long> pharmacyIncomes=new ArrayList<>();
        for(int i=1;i<=3;i++){
            pharmacyIncomes.add(getPharmacyIncomeForHalfYear(i,year,pharmacyId));
        }
        return pharmacyIncomes;
    }

    @Override
    public List<Long> getPharmacyIncomeReportByYears(int startYear, int endYear, Long pharmacyId) {
        List<Long> pharmacyIncomes=new ArrayList<>();
        for(int i=startYear;i<=endYear;i++){
            pharmacyIncomes.add(getPharmacyIncomeForYear(i,pharmacyId));
        }
        return pharmacyIncomes;
    }

    @Override
    public List<Long> getDrugConsumptionsReportsByMonths(int year, Long pharmacyId) {
        List<Long> drugsConsumption=new ArrayList<>();
        for(int i=1;i<=12;i++){
            drugsConsumption.add(getDrugConsumptionsForMonth(i,year,pharmacyId));
        }
        return drugsConsumption;
    }

    @Override
    public List<Long> getDrugConsumptionsReportsByHalfYears(int year, Long pharmacyId) {
        List<Long> drugsConsumption=new ArrayList<>();
        for(int i=1;i<=3;i++){
            drugsConsumption.add(getDrugConsumptionsForHalfYear(i,year,pharmacyId));
        }
        return drugsConsumption;
    }

    @Override
    public List<Long> getDrugConsumptionsReportsByYears(int startYear, int endYear, Long pharmacyId) {
        List<Long> drugsConsumption=new ArrayList<>();
        for(int i=startYear;i<=endYear;i++){
            drugsConsumption.add(getDrugConsumptionsForYear(i,pharmacyId));
        }
        return drugsConsumption;
    }
}
