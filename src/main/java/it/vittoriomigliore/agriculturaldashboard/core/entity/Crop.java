package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CROP")
public class Crop {
    @Id
    @Column(name = "CROP_ID", nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SEASON")
    private String season;

    @Column(name = "GROWTH_TIME")
    private Integer growthTime;

    @Column(name = "IDEAL_CONDITIONS")
    private String idealConditions;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(Integer growthTime) {
        this.growthTime = growthTime;
    }

    public String getIdealConditions() {
        return idealConditions;
    }

    public void setIdealConditions(String idealConditions) {
        this.idealConditions = idealConditions;
    }

}