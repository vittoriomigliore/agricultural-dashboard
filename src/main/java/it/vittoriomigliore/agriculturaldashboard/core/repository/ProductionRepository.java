package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Integer> {

    List<Production> findAllByCropAndHarvestDateBetween(Crop crop, LocalDate startDate, LocalDate endDate);
}
