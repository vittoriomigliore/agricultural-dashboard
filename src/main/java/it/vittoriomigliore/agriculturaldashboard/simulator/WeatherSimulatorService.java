package it.vittoriomigliore.agriculturaldashboard.simulator;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import it.vittoriomigliore.agriculturaldashboard.core.service.WeatherService;
import it.vittoriomigliore.agriculturaldashboard.simulator.weather.HumiditySimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.weather.RainSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.weather.TemperatureSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.weather.WindSpeedSimulator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherSimulatorService {

    private final FieldService fieldService;
    private final WeatherService weatherService;
    private final HumiditySimulator humiditySimulator;
    private final RainSimulator rainSimulator;
    private final TemperatureSimulator temperatureSimulator;
    private final WindSpeedSimulator windSpeedSimulator;

    public WeatherSimulatorService(FieldService fieldService,
                                   WeatherService weatherService,
                                   HumiditySimulator humiditySimulator,
                                   RainSimulator rainSimulator,
                                   TemperatureSimulator temperatureSimulator,
                                   WindSpeedSimulator windSpeedSimulator) {
        this.fieldService = fieldService;
        this.weatherService = weatherService;
        this.humiditySimulator = humiditySimulator;
        this.rainSimulator = rainSimulator;
        this.temperatureSimulator = temperatureSimulator;
        this.windSpeedSimulator = windSpeedSimulator;
        initializeSimulators();
    }

    public void initializeSimulators() {
        temperatureSimulator.setParameters(15.0, 2.0);
        humiditySimulator.setParameters(70.0, 10.0);
        rainSimulator.setParameters(1 / 0.5);
        windSpeedSimulator.setParameters(10, 3);
    }

    @Scheduled(fixedRateString = "${simulator.weather.rate}")
    public void simulateWeather() {
        List<Field> fields = fieldService.getAllFields();

        fields.forEach(this::saveSimulatedWeather);
    }

    private void saveSimulatedWeather(Field field) {
        Weather weather = new Weather();
        weather.setField(field);
        weather.setDateTime(LocalDateTime.now());

        BigDecimal temperature = BigDecimal.valueOf(temperatureSimulator.simulate());
        BigDecimal humidity = BigDecimal.valueOf(humiditySimulator.simulate());
        BigDecimal rain = BigDecimal.valueOf(rainSimulator.simulate());
        BigDecimal windSpeed = BigDecimal.valueOf(windSpeedSimulator.simulate());
        weather.setTemperature(temperature);
        weather.setHumidity(humidity);
        weather.setPrecipitation(rain);
        weather.setWindSpeed(windSpeed);

        weatherService.createWeather(weather);
    }


}
