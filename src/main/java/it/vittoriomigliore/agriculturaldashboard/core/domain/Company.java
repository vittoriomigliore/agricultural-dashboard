package it.vittoriomigliore.agriculturaldashboard.core.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String name;
    private String location;
    private String owner;
    private int establishedYear;

    // getters and setters
}