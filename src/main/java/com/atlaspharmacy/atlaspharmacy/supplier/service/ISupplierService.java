package com.atlaspharmacy.atlaspharmacy.supplier.service;

import com.atlaspharmacy.atlaspharmacy.supplier.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.supplier.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;

public interface ISupplierService {
    Supplier getSupplierById(Long id);
    Supplier registerSupplier(SupplierDTO supplierDTO) throws InvalidEmail;
}
