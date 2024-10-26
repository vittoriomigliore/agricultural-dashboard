package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "COSTS")
public class Cost {
    @Id
    @Column(name = "COST_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIELD_ID")
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CROP_ID")
    private Crop crop;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "COST_TYPE")
    private String costType;

    @Column(name = "AMOUNT", precision = 10, scale = 2)
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}