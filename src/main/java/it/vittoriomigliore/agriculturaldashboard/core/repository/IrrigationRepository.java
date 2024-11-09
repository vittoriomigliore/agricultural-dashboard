package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IrrigationRepository extends JpaRepository<Irrigation, Integer> {

    @Query("SELECT w FROM Irrigation w WHERE w.dateTime >= :dateTime AND w.field = :field")
    List<Irrigation> findByFieldAndAfterDateTime(@Param("field") Field field, @Param("dateTime") LocalDateTime dateTime);

}
