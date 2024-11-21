package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> getLastFiveWeatherByField(Field field) {
        return weatherRepository.findTop5ByField(field);
    }
}
