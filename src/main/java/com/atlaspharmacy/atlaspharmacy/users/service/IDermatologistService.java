package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IDermatologistService {
    List<Dermatologist> findAllByPharmacy(Long id);
    Dermatologist registerDermatologist(DermatologistDTO dto) throws Exception;
    List<Dermatologist> getAllDermatologistsToComplain(Long id);
    List<Dermatologist> searchDermatologists(Long pharmacyId,String searchInput);
    List<DermatologistDTO> filterDermatologistsByGrade(List<DermatologistDTO> dermatologistsToFilter,int grade);
    List<DermatologistDTO> filterDermatologistsByPharmacy(List<DermatologistDTO> dermatologistsToFilter,Long pharmacyId);
    void addDermatologistToPharmacy(Long dermatologistId,Long pharmacyId);
    boolean deleteDermatologistFromPharmacy(Long dermatologistId,Long pharmacyId);
    List<DermatologistDTO> getDermatologistsNotInPharmacy(Long pharmacyId);
    List<Dermatologist> getAll();

}
