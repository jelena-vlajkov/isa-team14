package com.atlaspharmacy.atlaspharmacy.membershipinfo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialoffers")
public class SpecialOffer {
    @Id
    private int id;
    private String content;

    public SpecialOffer(){}
    public SpecialOffer(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
