package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "irrigation")
public class Irrigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int irrigationId;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    private Date date;
    private double amountUsed;
    private String method;

    // getters and setters
}
