package com.atlaspharmacy.atlaspharmacy.schedule.DTO;

import com.atlaspharmacy.atlaspharmacy.medication.domain.Allergy;

import java.util.ArrayList;
import java.util.List;

public class PatientsOverviewDTO {
    private String name;
    private String surname;
    private Long patientId;
    private List<Allergy> allergies;
    private List<AppointmentDTO> previousAppointments;
    private List<String> prescribedDrugs;

    public PatientsOverviewDTO() {
        allergies = new ArrayList<>();
        prescribedDrugs = new ArrayList<>();
        previousAppointments = new ArrayList<>();
    }

    public PatientsOverviewDTO(String name, String surname, Long patientId, List<Allergy> allergies, List<AppointmentDTO> previousAppointments, List<String> prescribedDrugs) {
        this.name = name;
        this.surname = surname;
        this.patientId = patientId;
        this.allergies = allergies;
        this.previousAppointments = previousAppointments;
        this.prescribedDrugs = prescribedDrugs;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<AppointmentDTO> getPreviousAppointments() {
        return previousAppointments;
    }

    public void setPreviousAppointments(List<AppointmentDTO> previousAppointments) {
        this.previousAppointments = previousAppointments;
    }

    public List<String> getPrescribedDrugs() {
        return prescribedDrugs;
    }

    public void setPrescribedDrugs(List<String> prescribedDrugs) {
        this.prescribedDrugs = prescribedDrugs;
    }
}
