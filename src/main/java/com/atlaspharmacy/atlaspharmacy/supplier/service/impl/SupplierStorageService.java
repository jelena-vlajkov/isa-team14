package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.MedicationInOrder;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.SupplierStorageRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.ISupplierStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SupplierStorageService implements ISupplierStorageService {
    private final SupplierStorageRepository supplierStorageRepository;

    @Autowired
    public SupplierStorageService(SupplierStorageRepository supplierStorageRepository) {
        this.supplierStorageRepository = supplierStorageRepository;
    }

    @Override
    public List<SupplierStorageMedication> getSuppliersMedications(Long id) {
        List<SupplierStorageMedication> suppliersStorage = new ArrayList<>();
        for(SupplierStorageMedication s : supplierStorageRepository.findAll()){
            if(s.getSupplier().getId().equals(id)){
                suppliersStorage.add(s);
            }
        }
        return suppliersStorage;
    }

    @Override
    public boolean medicationPresentInStorage(OrderedMedicationDTO orderedMedicationDTO, Long supplierId){
        List<SupplierStorageMedication> supplierStorage = getSuppliersMedications(supplierId);

        for(SupplierStorageMedication storageMedication: supplierStorage){
            if(storageMedication.getMedications().getMedication_id().equals(orderedMedicationDTO.getMedication().getId()) && storageMedication.getMedications().getQuantity()>=orderedMedicationDTO.getQuantity()){
               return true;
            }
        }
        return false;

    }

}
