package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
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

    public boolean medicationPresentInStorage(MedicationInOrder medication, Long supplierId){
        List<SupplierStorageMedication> supplierStorage = getSuppliersMedications(supplierId);
        for(SupplierStorageMedication s: supplierStorage){
            if(s.getMedications().getMedication_id().equals(medication.getId()) && s.getMedications().getQuantity()>=medication.getOrderedMedication().getQuantity()){
               continue;
            }else{
                return false;
            }
        }
        return  true;
    }

}
