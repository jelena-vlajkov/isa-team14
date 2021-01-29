package com.atlaspharmacy.atlaspharmacy.notifications.mapper;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.notifications.DTO.MedicationNotificationDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;

public class NotificationMapper {

    private NotificationMapper() {}

    public static PharmacyStorage mapMedicationFromDTO(MedicationNotificationDTO medicationNotificationDTO) {
        return new PharmacyStorage(new Pharmacy(medicationNotificationDTO.getPharmacyId()), new Medication(medicationNotificationDTO.getMedicationId()), 0);
    }

}
