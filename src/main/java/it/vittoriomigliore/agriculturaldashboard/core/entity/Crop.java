package it.vittoriomigliore.agriculturaldashboard.core.entity;

import it.vittoriomigliore.agriculturaldashboard.core.util.ECropType;
import jakarta.persistence.*;

@Entity
@Table(name = "CROP")
public class Crop {
    @Id
    @Column(name = "CROP_ID", nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ECropType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECropType getType() {
        return type;
    }

    public void setType(ECropType type) {
        this.type = type;
    }

}