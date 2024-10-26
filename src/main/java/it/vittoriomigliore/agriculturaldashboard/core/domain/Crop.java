package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "crop")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropId;

    private String name;
    private String type;
    private String season;
    private int growthTime;
    private String idealConditions;

    // getters and setters
}
