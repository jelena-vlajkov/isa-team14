package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import com.atlaspharmacy.atlaspharmacy.appointment.Appointment;
import com.atlaspharmacy.atlaspharmacy.medication.Medication;
import net.bytebuddy.description.type.TypeList;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "pricelists")
public class Pricelist {
    @Id
    private int id;
    private Map<TypeList.Generic,Double> entity;


}
