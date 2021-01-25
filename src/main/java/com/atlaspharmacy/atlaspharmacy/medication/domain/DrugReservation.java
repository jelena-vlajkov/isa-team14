package com.atlaspharmacy.atlaspharmacy.medication.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.ReservationStatus;
import com.atlaspharmacy.atlaspharmacy.users.domain.User;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "drug_reservations")
public class DrugReservation {
    @Id
    private int id;
    private ReservationStatus reservationStatus;
    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "reserved_medications",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> reservedMedication;

    public DrugReservation() {
    }

    public DrugReservation(int id, ReservationStatus reservationStatus, Date dueDate) {
        this.id = id;
        this.reservationStatus = reservationStatus;
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<Medication> getMedication() {
        return reservedMedication;
    }

    public void setMedication(List<Medication> medications) {
        this.reservedMedication = medications;
    }
}
