package com.atlaspharmacy.atlaspharmacy.medicalrecord.DTO;

public class MedicationToRecommendDTO {
    private String name;
    private Long id;
    private boolean available;

    public MedicationToRecommendDTO() {
    }

    public MedicationToRecommendDTO(String name, Long id, boolean available) {
        this.name = name;
        this.id = id;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
