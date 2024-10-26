package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import it.vittoriomigliore.agriculturaldashboard.core.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherSimulatorService {

    private static final double WIND_SPEED_SHAPE = 2.0;
    private static final double WIND_SPEED_SCALE = 10.0;
    private static final double HUMIDITY_ALPHA = 2.0;
    private static final double HUMIDITY_BETA = 5.0;
    private final FieldService fieldService;
    private final WeatherService weatherService;
    private final HumiditySimulator humiditySimulator;
    private final RainSimulator rainSimulator;
    private final TemperatureSimulator temperatureSimulator;
    private final WindSpeedSimulator windSpeedSimulator;

    public WeatherSimulatorService(FieldService fieldService, WeatherService weatherService, HumiditySimulator humiditySimulator, RainSimulator rainSimulator, TemperatureSimulator temperatureSimulator, WindSpeedSimulator windSpeedSimulator) {
        this.fieldService = fieldService;
        this.weatherService = weatherService;
        this.humiditySimulator = humiditySimulator;
        this.rainSimulator = rainSimulator;
        this.temperatureSimulator = temperatureSimulator;
        this.windSpeedSimulator = windSpeedSimulator;
        initializeDailyWeather();
    }

    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2:00 AM
    public void initializeDailyWeather() {
        humiditySimulator.generateDailyMeanHumidity(HUMIDITY_ALPHA, HUMIDITY_BETA);
        rainSimulator.generateDailyRainProbability();
        temperatureSimulator.generateDailyTemperatureParameters();
        windSpeedSimulator.generateDailyMeanWindSpeed(WIND_SPEED_SHAPE, WIND_SPEED_SCALE);
    }

    @Scheduled(fixedRate = 120000) // Every 2 minutes
    public void simulateWeather() {
        List<Field> fields = fieldService.getAllFields();

        fields.forEach(this::saveSimulatedWeather);
    }

    private void saveSimulatedWeather(Field field) {
        Weather weather = new Weather();
        weather.setField(field);
        weather.setDate(LocalDate.now());

        BigDecimal humidity = BigDecimal.valueOf(humiditySimulator.getDailyMeanHumidity());
        BigDecimal precipitation = BigDecimal.valueOf(rainSimulator.simulatePrecipitationPerMinute());
        BigDecimal temperature = BigDecimal.valueOf(temperatureSimulator.getDailyMeanTemperature());
        BigDecimal windSpeed = BigDecimal.valueOf(windSpeedSimulator.getDailyMeanWindSpeed());
        weather.setHumidity(humidity);
        weather.setPrecipitation(precipitation);
        weather.setTemperature(temperature);
        weather.setWindSpeed(windSpeed);

        weatherService.createWeather(weather);
    }


}
