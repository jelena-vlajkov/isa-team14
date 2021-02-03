package com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;

import java.util.List;

public class MedicalRecordDTO {
    private String patientName;
    private List<Allergy> allergies;
    private List<Ingredient> ingredients;


    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(String patientName, List<Allergy> allergies, List<Ingredient> ingredients) {
        this.patientName = patientName;
        this.allergies = allergies;
        this.ingredients = ingredients;
    }



    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
