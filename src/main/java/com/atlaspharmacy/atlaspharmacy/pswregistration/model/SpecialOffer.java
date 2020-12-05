package com.atlaspharmacy.atlaspharmacy.pswregistration.model;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialoffers")
public class SpecialOffer {
    @Id
    private int id;
    private String name;
    private String content;
    private String pharmacyId;


    public SpecialOffer()
    {
        this.id = 1;
        this.name = "placeholdername";
        this.content = "placeholdercontent";
        this.pharmacyId = "Jankovic";
    }

    public SpecialOffer(int id, String name, String content, String pharmacyId)
    {
    
        this.id = id;
        this.name = name;
        this.content = content;
        this.pharmacyId = pharmacyId;

    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return this.content;
    }


    public void setPharmacyId(String pharmacyId)
    {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyId()
    {
        return this.pharmacyId;
    }

    
    @Override
    public int hashCode()
    {
        // TODO(Jovan): Remake?
        int hash = 100;
        hash = 123 * hash + Objects.hashCode(this.name);
        hash = 123 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        final SpecialOffer so = (SpecialOffer) o;
        if(!Objects.equals(this.name, so.getName())) return false;
        if(!Objects.equals(this.id, so.getId())) return false;
        return true;
    }

    @Override
    public String toString()
    {
        return this.name + " " + this.content;
    }
}