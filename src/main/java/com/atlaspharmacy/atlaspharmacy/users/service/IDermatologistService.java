package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IDermatologistService {
    List<Dermatologist> findAllByPharmacy(Long id);
    Dermatologist registerDermatologist(DermatologistDTO dto) throws InvalidEmail;
    List<Dermatologist> getAllDermatologistsToComplain(Long id);
    List<Dermatologist> searchDermatologists(String searchInput);
    List<DermatologistDTO> filterDermatologistsByPharmacy(List<DermatologistDTO> dermatologists,Long pharmacyId);
    List<DermatologistDTO> filterDermatologistsByGrade(List<DermatologistDTO> dermatologists,Double grade);
    void addDermatologistToPharmacy(Long dermatologistId,Long pharmacyId);
    boolean deleteDermatologistFromPharmacy(Long dermatologistId,Long pharmacyId);

}
