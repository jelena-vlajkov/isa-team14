package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.notifications.service.INotificationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IMedicationInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyStorageService implements IPharmacyStorageService {
    private final PharmacyStorageRepository pharmacyStorageRepository;
    private final INotificationService notificationService;
    private final IMedicationInOrderService medicationInOrderService;
    private final IPharmacyService pharmacyService;
    private final IMedicationService medicationService;



    @Autowired
    public PharmacyStorageService(PharmacyStorageRepository pharmacyStorageRepository, INotificationService notificationService, IMedicationInOrderService medicationInOrderService, IPharmacyService pharmacyService, IMedicationService medicationService) {
        this.pharmacyStorageRepository = pharmacyStorageRepository;
        this.notificationService = notificationService;
        this.medicationInOrderService = medicationInOrderService;
        this.pharmacyService = pharmacyService;
        this.medicationService = medicationService;
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
        if(medication!=null){
            if (medication.getQuantity() == 0)
                notificationService.medicationQuantityLow(medication);
            return medication;
        }
        return null;

    }

    @Override
    public boolean isMedicationInPharmacy(Long code, Long id) {
        List<PharmacyStorage> storages = getMedicationsByPharmacy(id);
        for(PharmacyStorage p : storages){
            if(p.getMedication().getCode().equals(code) && p.getQuantity()>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addNewMedicationsToStorage(Order order) {
        List<MedicationInOrder> medicationsByOrder=medicationInOrderService.getAllMedicationsByOrder(order.getId());
        List<PharmacyStorage> medicationsInPharmacy=getMedicationsByPharmacy(order.getPharmacy().getId());
        for(MedicationInOrder medicationInOrder:medicationsByOrder){
            for(PharmacyStorage medicationInPharmacy:medicationsInPharmacy){
                if(medicationInOrder.getOrderedMedication().getMedication().equals(medicationInPharmacy.getMedication().getId())) {
                    break;
                }
                PharmacyStorage newMedication=new PharmacyStorage();
                newMedication.setMedication(MedicationMapper.convertToMedication
                        (medicationService.findById(medicationInOrder.getOrderedMedication().getMedication())));
                newMedication.setPharmacy(order.getPharmacy());
                newMedication.setQuantity(medicationInOrder.getOrderedMedication().getQuantity());
                pharmacyStorageRepository.save(newMedication);
                
            }
        }

    }

}
