package com.atlaspharmacy.atlaspharmacy.medication.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.PrescribedDrugDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.PrescribedDrug;

public class PrescribedDrugMapper {

    public PrescribedDrugMapper() {
    }

    public static PrescribedDrugDTO drugToDto(PrescribedDrug p) {
        PrescribedDrugDTO prescribedDrugDTO = new PrescribedDrugDTO(
                p.getId(),
                p.getQuantity(),
                p.getPrescribedMedication().getName(),
                p.getEprescription().getId()
        );

        return  prescribedDrugDTO;
    }
}
