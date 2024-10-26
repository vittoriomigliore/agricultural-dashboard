package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "production")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productionId;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    private Date harvestDate;
    private double quantity;
    private int qualityRating;

    // getters and setters
}
