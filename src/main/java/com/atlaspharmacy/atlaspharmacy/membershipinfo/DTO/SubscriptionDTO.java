package com.atlaspharmacy.atlaspharmacy.membershipinfo.DTO;

import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PatientDTO;

public class SubscriptionDTO {
    private Long id;
    private PharmacyDTO pharmacy;
    private PatientDTO patient;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, PharmacyDTO pharmacy, PatientDTO patient ) {
        this.id = id;
        this.pharmacy = pharmacy;
        this.patient = patient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
