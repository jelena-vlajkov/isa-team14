package com.atlaspharmacy.atlaspharmacy.promotions.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import java.util.Date;

public class PromotionDTO {
    private Long id;
    private String description;
    private Date startTime;
    private Date endTime;
    private PharmacyDTO pharmacy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public PromotionDTO(){}

    public PromotionDTO(Long id, String description, Date startTime, Date endTime, PharmacyDTO pharmacy) {
        this.id = id;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pharmacy = pharmacy;
    }
}
