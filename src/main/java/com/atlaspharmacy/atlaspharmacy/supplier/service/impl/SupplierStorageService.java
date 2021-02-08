package com.atlaspharmacy.atlaspharmacy.supplier.service.impl;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.repository.MedicationRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.MedicationInStorageDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.NewMedicationToStorageDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.OrderedMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierStorageMedicationDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierMedicationInStorage;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.SupplierStorageMedication;
import com.atlaspharmacy.atlaspharmacy.supplier.mapper.MedicationInStorageMapper;
import com.atlaspharmacy.atlaspharmacy.supplier.repository.SupplierStorageRepository;
import com.atlaspharmacy.atlaspharmacy.supplier.service.ISupplierStorageService;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.mapper.SupplierMapper;
import com.atlaspharmacy.atlaspharmacy.users.repository.SupplierRepository;
import com.atlaspharmacy.atlaspharmacy.users.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SupplierStorageService implements ISupplierStorageService {
    private final SupplierStorageRepository supplierStorageRepository;
    private final MedicationRepository medicationRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;
    @Autowired
    public SupplierStorageService(SupplierStorageRepository supplierStorageRepository, MedicationRepository medicationRepository, SupplierRepository supplierRepository, SupplierService supplierService) {
        this.supplierStorageRepository = supplierStorageRepository;
        this.medicationRepository = medicationRepository;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
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
    public SupplierStorageMedicationDTO getSuppliersStorage(Long id){
        List<Medication> allMedications = medicationRepository.findAll();
        List<MedicationInStorageDTO> storage = new ArrayList<>();
        for(SupplierStorageMedication s : getSuppliersMedications(id)){
            MedicationInStorageDTO dto = MedicationInStorageMapper.mapToDTO(s.getMedications(), medicationRepository.findById(s.getMedications().getMedication_id()).get());
            storage.add(dto);
        }
        SupplierStorageMedicationDTO suppliersMeds = new SupplierStorageMedicationDTO();
        suppliersMeds.setSupplierDTO(SupplierMapper.mapSupplierToDTO(supplierRepository.findById(id).get()));
        suppliersMeds.setMedicationInStorage(storage);

       return  suppliersMeds;
    }

    @Override
    public SupplierStorageMedication addNewMedicationToStorage(NewMedicationToStorageDTO dto) {
        SupplierMedicationInStorage newMed = new SupplierMedicationInStorage();
        newMed.setQuantity(dto.getQuantity());
        newMed.setMedication_id(dto.getMedication().getId());
        Supplier s = supplierService.findByEmail(dto.getSupplier().getEmail());

        List<SupplierStorageMedication> supplierStorage = getSuppliersMedications(s.getId());

        for(SupplierStorageMedication supplierStorageMedication : supplierStorage){
            if(dto.getMedication().getId().equals(supplierStorageMedication.getMedications().getMedication_id())){
                return null;
            }
        }

            SupplierStorageMedication supplierStorageMedication = new SupplierStorageMedication();
            supplierStorageMedication.setSupplier(s);
            supplierStorageMedication.setMedications(newMed);
            return supplierStorageRepository.save(supplierStorageMedication);
        }

    @Override
    public SupplierStorageMedication updateQuantity(NewMedicationToStorageDTO dto) {
        Supplier s = supplierService.findByEmail(dto.getSupplier().getEmail());
        List<SupplierStorageMedication> supplierStorage = getSuppliersMedications(s.getId());
        for(SupplierStorageMedication storageMedication : supplierStorage){
            if(storageMedication.getMedications().getMedication_id().equals(dto.getMedication().getId())){
                SupplierMedicationInStorage drugToUpdate = storageMedication.getMedications();
                drugToUpdate.setQuantity(dto.getQuantity());
                storageMedication.setMedications(drugToUpdate);
                return supplierStorageRepository.save(storageMedication);
            }
        }
        return null;
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
