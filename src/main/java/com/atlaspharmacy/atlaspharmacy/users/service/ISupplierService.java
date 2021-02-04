package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.SupplierDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Supplier;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;

public interface ISupplierService {
    Supplier registerSupplier(SupplierDTO supplierDTO) throws InvalidEmail;

}
