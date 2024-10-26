package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {
    @Id
    @Column(name = "COMPANY_ID", nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ESTABLISHED_YEAR")
    private Integer establishedYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

}