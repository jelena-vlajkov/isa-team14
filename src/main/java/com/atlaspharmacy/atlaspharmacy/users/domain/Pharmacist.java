package com.atlaspharmacy.atlaspharmacy.users.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacists")
public class Pharmacist extends MedicalStaff {

}
