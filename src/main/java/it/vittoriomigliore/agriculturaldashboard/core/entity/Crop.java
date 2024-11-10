package it.vittoriomigliore.agriculturaldashboard.core.entity;

import it.vittoriomigliore.agriculturaldashboard.core.util.ECropType;
import it.vittoriomigliore.agriculturaldashboard.core.util.Utils;
import jakarta.persistence.*;

import java.awt.*;

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

    public Color getColor() {
        return switch (this.name) {
            case "Wheat" -> new Color(54, 162, 235);
            case "Corn" -> new Color(75, 192, 192);
            case "Tomato" -> new Color(255, 99, 132);
            case "Potato" -> new Color(255, 206, 86);
            default -> Utils.getColorFromString(this.name);
        };
    }

}