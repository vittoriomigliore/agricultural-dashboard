package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FIELD")
public class Field {
    @Id
    @Column(name = "FIELD_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CROP_ID")
    private Crop crop;

    @Column(name = "SIZE", precision = 10, scale = 2)
    private BigDecimal size;

    @Column(name = "PLANTING_DATE")
    private LocalDate plantingDate;

    @Column(name = "HARVEST_DATE")
    private LocalDate harvestDate;

    @Column(name = "LAST_IRRIGATION_DATE")
    private LocalDate lastIrrigationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public LocalDate getLastIrrigationDate() {
        return lastIrrigationDate;
    }

    public void setLastIrrigationDate(LocalDate lastIrrigationDate) {
        this.lastIrrigationDate = lastIrrigationDate;
    }

}