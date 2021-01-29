package com.atlaspharmacy.atlaspharmacy.medication.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Ingredient;
import com.atlaspharmacy.atlaspharmacy.medication.domain.Medication;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugForm;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugKind;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.DrugType;
import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.TypeOfPrescribing;

import java.util.ArrayList;
import java.util.List;

public class MedicationDTO {
    private Long id;
    private String name;
    private DrugForm drugForm;
    private DrugType drugType;
    private String producer;
    private TypeOfPrescribing typeOfPrescribing;
    private String additionalNotes;
    private String contraindications;
    private Long dailyDose;
    private DrugKind drugKind;
    private List<Long> substituteMedication;
    private Long code;
    private List<Long> ingredients;
    public MedicationDTO() {

    }

    public MedicationDTO(Long id, String name, DrugForm drugForm, DrugType drugType, String producer, TypeOfPrescribing typeOfPrescribing, String additionalNotes, String contraindications, Long dailyDose, DrugKind drugKind, Long code) {
        this.id = id;
        this.name = name;
        this.drugForm = drugForm;
        this.drugType = drugType;
        this.producer = producer;
        this.typeOfPrescribing = typeOfPrescribing;
        this.additionalNotes = additionalNotes;
        this.contraindications = contraindications;
        this.dailyDose = dailyDose;
        this.drugKind = drugKind;
        this.code = code;
    }
    public static MedicationDTO convertToMedicationDTO(Medication m){
        MedicationDTO medicationDTO = new MedicationDTO(
                m.getId(),
                m.getName(),
                m.getDrugForm(),
                m.getDrugType(),
                m.getProducer(),
                m.getTypeOfPrescribing(),
                m.getAdditionalNotes(),
                m.getContraindications(),
                m.getDailyDose(),
                m.getDrugKind(),
                m.getCode()
        );
        medicationDTO.setSubstituteMedication(new ArrayList<>());

        for(Medication med: m.getSubstituteMedication()){
            medicationDTO.getSubstituteMedication().add(med.getId());
        }
        medicationDTO.setIngredients(new ArrayList<>());

        for(Ingredient i : m.getIngredients()){
            medicationDTO.getIngredients().add(i.getId());
        }
        return medicationDTO;
    }
    public static void convertToMedication(Medication m, MedicationDTO mdto){
        m.setId(mdto.getId());
        m.setName(mdto.getName());
        m.setDailyDose(mdto.getDailyDose());
        m.setAdditionalNotes(mdto.getAdditionalNotes());
        m.setContraindications(mdto.getContraindications());
        m.setDrugKind(mdto.getDrugKind());
        m.setDrugForm(mdto.getDrugForm());
        m.setDrugType(mdto.getDrugType());
        m.setProducer(mdto.getProducer());
        m.setTypeOfPrescribing(mdto.getTypeOfPrescribing());
        m.setCode(mdto.getCode());
    }

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<Long> getSubstituteMedication() {
        return substituteMedication;
    }

    public void setSubstituteMedication(List<Long> substituteMedication) {
        this.substituteMedication = substituteMedication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrugForm getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(DrugForm drugForm) {
        this.drugForm = drugForm;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public TypeOfPrescribing getTypeOfPrescribing() {
        return typeOfPrescribing;
    }

    public void setTypeOfPrescribing(TypeOfPrescribing typeOfPrescribing) {
        this.typeOfPrescribing = typeOfPrescribing;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public Long getDailyDose() {
        return dailyDose;
    }

    public void setDailyDose(Long dailyDose) {
        this.dailyDose = dailyDose;
    }

    public DrugKind getDrugKind() {
        return drugKind;
    }

    public void setDrugKind(DrugKind drugKind) {
        this.drugKind = drugKind;
    }

}
