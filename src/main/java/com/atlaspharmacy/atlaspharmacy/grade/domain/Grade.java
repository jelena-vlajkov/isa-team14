package com.atlaspharmacy.atlaspharmacy.grade.domain;

import com.atlaspharmacy.atlaspharmacy.users.domain.Patient;

import javax.persistence.*;

@Entity
@Table(name = "grades")
@DiscriminatorColumn(name = "grade_type", discriminatorType=DiscriminatorType.STRING)
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int grade;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Patient patient;


    private String type;

    public Grade(int grade, Patient patient) {
        this.id = id;
        this.grade = grade;
        this.patient = patient;
    }


    public Grade() {}

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

