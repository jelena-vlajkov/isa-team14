package com.atlaspharmacy.atlaspharmacy.users.service;

import com.atlaspharmacy.atlaspharmacy.schedule.domain.Examination;
import com.atlaspharmacy.atlaspharmacy.users.DTO.DermatologistDTO;
import com.atlaspharmacy.atlaspharmacy.users.DTO.PharmacistDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.Dermatologist;
import com.atlaspharmacy.atlaspharmacy.users.domain.Pharmacist;
import com.atlaspharmacy.atlaspharmacy.users.exceptions.InvalidEmail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IPharmacistService {
     List<Pharmacist> getAllPharmacistsToComplain(Long id);
     List<Pharmacist> searchPharmacists(Long pharmacyId,String searchInput);
     List<Pharmacist> findByPharmacy(Long id);
     List<Pharmacist> searchPharmacistsByPharmacyAdmin(String searchInput,Long pharmacyId);
     List<PharmacistDTO> filterPharmacistsByPharmacy(List<PharmacistDTO> pharmacistsToFilter,Long pharmacyId);
     List<PharmacistDTO> filterPharmacistsByGrade(List<PharmacistDTO> pharmacistsToFilter,int grade);
     Pharmacist editPharmacist(PharmacistDTO pharmacistDTO);
     Pharmacist registerPharmacist(PharmacistDTO dto) throws Exception;
     boolean deletePharmacist(Long pharmacistId,Long pharmacyId);
     Pharmacist findById(Long pharmacistId);
     List<Pharmacist> getAll();
     List<PharmacistDTO> findByRangeAndPharmacy(Date startRange, Date endRange, Long pharmacyId);
     List<PharmacistDTO> findForPatientGrading(Long patientId);


}
