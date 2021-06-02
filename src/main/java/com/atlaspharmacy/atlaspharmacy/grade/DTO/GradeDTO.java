package com.atlaspharmacy.atlaspharmacy.grade.DTO;

public class GradeDTO {

    private Long id;
    private int grade;
    private String gradeType;
    private Long patientId;
    private Long medicationId;

    public GradeDTO() {
    }

    public GradeDTO(Long id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
