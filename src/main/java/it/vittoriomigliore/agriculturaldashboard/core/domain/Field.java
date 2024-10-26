package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    private double size;
    private Date plantingDate;
    private Date harvestDate;
    private Date lastIrrigationDate;

    // getters and setters
}