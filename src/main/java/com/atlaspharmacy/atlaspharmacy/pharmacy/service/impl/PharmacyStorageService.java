package com.atlaspharmacy.atlaspharmacy.pharmacy.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.DTO.MedicationDTO;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.mapper.MedicationMapper;
import com.atlaspharmacy.atlaspharmacy.medication.service.IMedicationService;
import com.atlaspharmacy.atlaspharmacy.notifications.domain.Notification;
import com.atlaspharmacy.atlaspharmacy.notifications.service.INotificationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.PharmacyStorage;
import com.atlaspharmacy.atlaspharmacy.pharmacy.mapper.PharmacyMapper;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.reservations.service.IDrugReservationService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.repository.PharmacyStorageRepository;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyService;
import com.atlaspharmacy.atlaspharmacy.pharmacy.service.IPharmacyStorageService;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Order;
import com.atlaspharmacy.atlaspharmacy.supplier.service.IMedicationInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyStorageService implements IPharmacyStorageService {
    private final PharmacyStorageRepository pharmacyStorageRepository;
    private final INotificationService notificationService;
    private final IMedicationService medicationService;
    private final PharmacyRepository pharmacyRepository;
    private final IMedicationInOrderService medicationInOrderService;


    @Autowired
    public PharmacyStorageService(PharmacyStorageRepository pharmacyStorageRepository
                                  , INotificationService notificationService
                                  , IMedicationService medicationService
                                  , PharmacyRepository pharmacyRepository
                                  , IMedicationInOrderService medicationInOrderService) {
        this.pharmacyStorageRepository = pharmacyStorageRepository;
        this.notificationService = notificationService;
        this.medicationService = medicationService;
        this.pharmacyRepository = pharmacyRepository;
        this.medicationInOrderService = medicationInOrderService;


    }
    

    @Override
    public List<PharmacyStorage> getMedicationsByPharmacy(Long pharmacyId) {
        return pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacy(pharmacyId);
    }

    @Override
    public PharmacyStorage getMedicationInPharmacy(Long medicationId, Long pharmacyId) {
        PharmacyStorage medication = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(pharmacyId,medicationId);

        if(medication!=null){
            if (medication.getQuantity() == 0)
                notificationService.medicationQuantityLow(medication);
            return medication;
        }

        return null;

    }


    @Override
    public boolean isMedicationInPharmacy(Long code, Long id) {
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndCode(id, code);
        if (pharmacyStorage.getQuantity() == 0) {
            notificationService.medicationQuantityLow(pharmacyStorage);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void deleteMedicationFromPharmacyStorage(Long medicationId,Long pharmacyId) {
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(pharmacyId, medicationId);
        if (pharmacyStorage != null) {
            pharmacyStorageRepository.delete(pharmacyStorage);
        }
    }

    @Override
    @Transactional
    public void editMedicationAmount(Long medicationId,Long pharmacyId,Long amount) {
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(pharmacyId, medicationId);
        if (pharmacyStorage != null) {
            pharmacyStorage.setQuantity(amount);
            pharmacyStorageRepository.save(pharmacyStorage);
        }
    }

    @Override
    public List<MedicationDTO> getMedicationsInPharmacy(long pharmacyId) {
        List<MedicationDTO> allMedications=medicationService.findAll();
        List<MedicationDTO> medicationsInPharmacy=new ArrayList();
        for(MedicationDTO m:allMedications){
            if(isMedicationInPharmacy(m.getCode(),pharmacyId)){
                medicationsInPharmacy.add(m);
            }
        }
        return medicationsInPharmacy;
    }

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
    @Override
    public void addMedicationToPharmacy(Long medicationId,Long pharmacyId,Long amount) {
        PharmacyStorage newMedicationInStorage=new PharmacyStorage();
        newMedicationInStorage.setQuantity(amount);
        newMedicationInStorage.setMedication(medicationService.getById(medicationId));
        newMedicationInStorage.setPharmacy(pharmacyRepository.findById(pharmacyId).get());
        pharmacyStorageRepository.save(newMedicationInStorage);

    }

    @Override
    public List<PharmacyStorage> getAllPharmaciesByMedication(Long id) {
        return pharmacyStorageRepository.getAllPharmaciesByMedication(id);
    }

    @Override
    public List<PharmacyStorage> getAllPharmaciesByMedicationCode(Long code) {
        return pharmacyStorageRepository.getAllPharmaciesByMedicationCode(code);
    }
    @Transactional
    @Override
    public void medicationReserved(Long medicationId, Long pharmacyId) {
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(pharmacyId, medicationId);
        pharmacyStorage.setQuantity(pharmacyStorage.getQuantity() - 1);
        if (pharmacyStorage.getQuantity() == 0) {
            notificationService.medicationQuantityLow(pharmacyStorage);
        }
        pharmacyStorageRepository.save(pharmacyStorage);
    }

    @Transactional
    @Override
    public void reduceMedicationQuantity(Long medicationId, Long pharmacyId) {
        PharmacyStorage pharmacyStorage = pharmacyStorageRepository.getAllPharmaciesStoragesByPharmacyAndMedication(pharmacyId, medicationId);
        pharmacyStorage.setQuantity(pharmacyStorage.getQuantity() + 1);
        if (pharmacyStorage.getQuantity() == 0) {
            notificationService.medicationQuantityLow(pharmacyStorage);
        }
        pharmacyStorageRepository.save(pharmacyStorage);
    }
}
