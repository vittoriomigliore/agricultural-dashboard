package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "IRRIGATION")
public class Irrigation {
    @Id
    @Column(name = "IRRIGATION_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIELD_ID")
    private Field field;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "AMOUNT_USED", precision = 10, scale = 2)
    private BigDecimal amountUsed;

    @Column(name = "METHOD")
    private String method;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(BigDecimal amountUsed) {
        this.amountUsed = amountUsed;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}