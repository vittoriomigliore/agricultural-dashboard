package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weatherId;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    private Date date;
    private double temperature;
    private double precipitation;
    private double humidity;
    private double windSpeed;

    // getters and setters
}
