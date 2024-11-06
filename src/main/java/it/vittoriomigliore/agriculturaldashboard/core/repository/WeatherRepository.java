package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    @Query("SELECT w FROM Weather w WHERE w.dateTime >= :dateTime AND w.field = :field")
    List<Weather> findByFieldAndAfterDateTime(@Param("field") Field field, @Param("dateTime") LocalDateTime dateTime);
}
