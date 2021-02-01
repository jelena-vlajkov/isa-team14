package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    private int id;
    private boolean active;
    public Subscription(){}
    public Subscription(int id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
