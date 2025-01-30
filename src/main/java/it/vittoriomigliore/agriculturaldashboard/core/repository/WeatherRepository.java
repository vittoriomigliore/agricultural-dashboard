package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    List<Weather> findTop5ByField(Field field);
}
