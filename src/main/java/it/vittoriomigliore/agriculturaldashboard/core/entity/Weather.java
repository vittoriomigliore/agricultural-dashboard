package it.vittoriomigliore.agriculturaldashboard.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "WEATHER")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEATHER_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIELD_ID")
    private Field field;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TEMPERATURE", precision = 5, scale = 2)
    private BigDecimal temperature;

    @Column(name = "PRECIPITATION", precision = 5, scale = 2)
    private BigDecimal precipitation;

    @Column(name = "HUMIDITY", precision = 5, scale = 2)
    private BigDecimal humidity;

    @Column(name = "WIND_SPEED", precision = 5, scale = 2)
    private BigDecimal windSpeed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(BigDecimal precipitation) {
        this.precipitation = precipitation;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

}