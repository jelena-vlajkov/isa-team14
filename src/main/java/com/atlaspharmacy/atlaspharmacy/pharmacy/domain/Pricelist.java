package com.atlaspharmacy.atlaspharmacy.pharmacy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pricelists")
public class Pricelist {
    @Id
    private int id;


}
