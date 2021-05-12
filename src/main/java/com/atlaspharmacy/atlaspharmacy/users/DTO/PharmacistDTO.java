package com.atlaspharmacy.atlaspharmacy.users.DTO;

import com.atlaspharmacy.atlaspharmacy.generalities.DTO.AddressDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.DTO.PharmacyDTO;
import com.atlaspharmacy.atlaspharmacy.pharmacy.domain.Pharmacy;
import com.atlaspharmacy.atlaspharmacy.users.domain.enums.Gender;
import com.atlaspharmacy.atlaspharmacy.users.domain.valueobjects.AverageGrade;

import java.util.Date;
import java.util.List;

public class PharmacistDTO extends MedicalStaffDTO{
    private PharmacyDTO pharmacy;
    private AverageGrade averageGrade;


    public PharmacistDTO() {
    }

    public PharmacistDTO(Long id, String name, String surname, Date dateOfBirth,
                         String phoneNumber, String email, String password, Gender gender,
                         AddressDTO address, String role, List<AuthorityDTO> authorities,
                         PharmacyDTO pharmacy, boolean firstTimePassword,AverageGrade averageGrade,String licenceNumber) {
        super(id,name,surname,dateOfBirth,phoneNumber,email,password,gender,address,role,authorities,firstTimePassword,licenceNumber);
        this.pharmacy = pharmacy;
        this.averageGrade = averageGrade;
    }

    public PharmacyDTO getPharmacy() { return pharmacy; }

    public void setPharmacy(PharmacyDTO pharmacy) {
        this.pharmacy = pharmacy;
    }

    public AverageGrade getAverageGrade() { return averageGrade; }

    public void setAverageGrade(AverageGrade averageGrade) { this.averageGrade = averageGrade; }
}
