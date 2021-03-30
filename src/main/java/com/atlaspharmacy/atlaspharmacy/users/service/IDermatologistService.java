package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IDermatologistService {
    public List<Dermatologist> findAllByPharmacy(Long id);
    public Dermatologist registerDermatologist(DermatologistDTO dto) throws InvalidEmail;
    List<Dermatologist> getAllDermatologistsToComplain(Long id);
    List<Dermatologist> searchDermatologists(String searchInput);
    List<Dermatologist> filterDermatologistsByPharmacy(List<Dermatologist> dermatologists,Long pharmacyId);
    List<Dermatologist> filterDermatologistsByGrade(List<Dermatologist> dermatologists,Double grade);

}
