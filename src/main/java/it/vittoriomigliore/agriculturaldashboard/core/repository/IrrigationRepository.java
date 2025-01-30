package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IrrigationRepository extends JpaRepository<Irrigation, Integer> {

    List<Irrigation> findTop5ByField(Field field);

}
