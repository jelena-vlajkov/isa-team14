package com.atlaspharmacy.atlaspharmacy.medication.domain;

import com.atlaspharmacy.atlaspharmacy.medication.domain.enums.ReservationStatus;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drug_reservations")
public class DrugReservation {
    @Id
    private int id;
    private ReservationStatus reservationStatus;
    private Date dueDate;

    public DrugReservation() {
    }

    public DrugReservation(int id, ReservationStatus reservationStatus, Date dueDate) {
        this.id = id;
        this.reservationStatus = reservationStatus;
        this.dueDate = dueDate;
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
}
