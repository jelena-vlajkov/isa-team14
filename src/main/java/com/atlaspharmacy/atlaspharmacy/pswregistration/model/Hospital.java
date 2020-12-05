package com.atlaspharmacy.atlaspharmacy.pswregistration.model;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    private String apiKey;
    private String name;

    public Hospital()
    {
        this.apiKey = "placeholderapi";
        this.name = "placeholdername";
    }

    public Hospital(String name, String api)
    {
        this.name = name;
        this.apiKey = api;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getApiKey()
    {
        return this.apiKey;
    }

    public void setApiKey(String api)
    {
        this.apiKey = api;
    }


    @Override
    public int hashCode()
    {
        // TODO(Jovan): Remake?
        int hash = 100;
        hash = 123 * hash + Objects.hashCode(this.name);
        hash = 123 * hash + Objects.hashCode(this.apiKey);
        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        final Hospital h = (Hospital) o;
        if(!this.name.equals(h.getName())) return false;
        if(!this.apiKey.equals(h.getApiKey())) return false;
        return true;
    }
}
