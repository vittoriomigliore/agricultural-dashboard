package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProductionRepository extends JpaRepository<Production, Integer> {

    long countByFieldAndHarvestDate(Field field, LocalDate date);

    Production findFirstByFieldAndHarvestDate(Field field, LocalDate date);

    Production findFirstByFieldOrderByHarvestDateDesc(Field field);

    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM Production c WHERE (:field IS NULL OR c.field = :field) AND c.harvestDate BETWEEN :startDate AND :endDate")
    BigDecimal sumProductionByFieldAndHarvestDateBetween(@Param("field") Field field, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
