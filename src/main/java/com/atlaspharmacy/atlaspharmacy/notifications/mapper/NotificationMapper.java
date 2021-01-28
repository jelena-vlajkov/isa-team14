package com.atlaspharmacy.atlaspharmacy.notifications.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.notifications.DTO.MedicationNotificationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;

public class NotificationMapper {

    private NotificationMapper() {}

    public static Medication mapMedicationFromDTO(MedicationNotificationDTO medicationNotificationDTO) {
        return new Medication(medicationNotificationDTO.getMedicationId(), medicationNotificationDTO.getMedicationName());
    }

    public static Pharmacy mapPharmacyFromDTO(MedicationNotificationDTO medicationNotificationDTO) {
        return new Pharmacy(medicationNotificationDTO.getPharmacyId(), medicationNotificationDTO.getPharmacyName(), "");
    }
}
