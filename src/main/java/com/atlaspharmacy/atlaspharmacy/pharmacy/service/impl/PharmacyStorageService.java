package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.notifications.service.INotificationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyStorageMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyStorageService implements IPharmacyStorageService {
    private final PharmacyStorageRepository pharmacyStorageRepository;
    private final INotificationService notificationService;

    @Autowired
    public PharmacyStorageService(PharmacyStorageRepository pharmacyStorageRepository, INotificationService notificationService) {
        this.pharmacyStorageRepository = pharmacyStorageRepository;
        this.notificationService = notificationService;
    }

    @Override
    public List<PharmacyStorage> getMedicationsByPharmacy(Long pharmacyId) {
        return pharmacyStorageRepository.findAll()
                .stream()
                .filter(pharmacyStorage -> pharmacyStorage.isPharmacy(pharmacyId))
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyStorage getMedicationInPharmacy(Long medicationId, Long pharmacyId) {
        PharmacyStorage medication = pharmacyStorageRepository.findAll()
                .stream()
                .filter(pharmacyStorage -> pharmacyStorage.isPharmacy(pharmacyId))
                .findFirst()
                .orElse(null);
        if (medication.getQuantity() == 0)
            notificationService.medicationQuantityLow(medication);
        return medication;
    }
}
