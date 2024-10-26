package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int saleId;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    private Date date;
    private double quantitySold;
    private double salePricePerUnit;
    private String buyer;

    // getters and setters
}
