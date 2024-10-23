package it.vittoriomigliore.agriculturaldashboard.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "costs")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int costId;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @Column(name = "date")
    private Date date;

    @Column(name = "cost_type")
    private String costType;

    @Column(name = "amount")
    private double amount;
}