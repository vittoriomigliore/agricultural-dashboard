package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SALE")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALE_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PRODUCTION_ID")
    private Production production;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "QUANTITY_SOLD", precision = 10, scale = 2)
    private BigDecimal quantitySold;

    @Column(name = "SALE_PRICE_PER_UNIT", precision = 10, scale = 2)
    private BigDecimal salePricePerUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(BigDecimal quantitySold) {
        this.quantitySold = quantitySold;
    }

    public BigDecimal getSalePricePerUnit() {
        return salePricePerUnit;
    }

    public void setSalePricePerUnit(BigDecimal salePricePerUnit) {
        this.salePricePerUnit = salePricePerUnit;
    }

}