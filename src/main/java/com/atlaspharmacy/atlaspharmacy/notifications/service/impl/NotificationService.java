package com.atlaspharmacy.atlaspharmacy.notifications.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.notifications.domain.Notification;
import com.atlaspharmacy.atlaspharmacy.notifications.repository.NotificationRepository;
import com.atlaspharmacy.atlaspharmacy.notifications.service.INotificationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
    private final NotificationRepository notificationRepository;
    private final IUserService userService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, IUserService userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    @Override
    public void medicationQuantityLow(PharmacyStorage pharmacyStorage) {
        String content = "Please restock medication " + pharmacyStorage.getMedication().getName() +
                " in your pharmacy " + pharmacyStorage.getPharmacy().getName() + " with id " + pharmacyStorage.getPharmacy().getId();
        notificationRepository.save(new Notification(content, userService.getPharmacyAdmin(pharmacyStorage.getPharmacy().getId())));
    }
}
