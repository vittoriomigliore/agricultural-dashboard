package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationRepository extends JpaRepository<Irrigation, Integer> {
}
