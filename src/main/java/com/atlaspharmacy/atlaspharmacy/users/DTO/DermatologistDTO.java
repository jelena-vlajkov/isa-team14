package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;

import java.util.Date;
import java.util.List;

public class DermatologistDTO extends MedicalStaffDTO {
    private List<PharmacyDTO> pharmacies;
    private AverageGrade averageGrade;

    public DermatologistDTO() {
    }

    public DermatologistDTO(Long id, String name, String surname, Date dateOfBirth,
                         String phoneNumber, String email, String password, Gender gender,
                         AddressDTO address, String role, List<AuthorityDTO> authorities,
                         List<PharmacyDTO> pharmacies, boolean firstTimePassword,AverageGrade averageGrade,String licenceNumber) {
        super(id,name,surname,dateOfBirth,phoneNumber,email,password,gender,address,role,authorities,firstTimePassword,licenceNumber);
        this.pharmacies = pharmacies;
        this.averageGrade = averageGrade;
    }

    public List<PharmacyDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public AverageGrade getAverageGrade() { return averageGrade; }

    public void setAverageGrade(AverageGrade averageGrade) { this.averageGrade = averageGrade; }

    public Double countAverageGrade(){ return this.getAverageGrade().count(); }
}
