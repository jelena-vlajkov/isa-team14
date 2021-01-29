package com.atlaspharmacy.atlaspharmacy.generalities.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
@Proxy(lazy = false)
public class Coordinates {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private Long latitude;
     private Long longitude;

    public Coordinates() {
    }

    public Coordinates(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
