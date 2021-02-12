package com.atlaspharmacy.atlaspharmacy.pharmacy.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pricelist;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;
import com.atlaspharmacy.atlaspharmacy.users.mapper.PharmacyAdminMapper;

import java.util.Date;

public class PricelistDTO {
    private Long id;
    private Long price;
    private MedicationDTO medication;
    private PharmacyDTO pharmacy;
    private Date startPeriod;
    private Date endPeriod;

    public PricelistDTO() {
    }

    public PricelistDTO(Long id, Long price, MedicationDTO medication, PharmacyDTO pharmacy, Period period) {
        this.id = id;
        this.price = price;
        this.medication = medication;
        this.pharmacy = pharmacy;
        this.startPeriod = period.getStartTime();
        this.endPeriod = period.getEndTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }
}
