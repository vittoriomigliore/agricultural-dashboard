package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CostRepository extends JpaRepository<Cost, Integer> {

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Cost c WHERE c.date <= :date")
    BigDecimal sumCostsByDateBefore(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Cost c WHERE c.field = :field AND c.date BETWEEN :startDate AND :endDate")
    BigDecimal sumCostsByFieldAndDateBetween(@Param("field") Field field,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

}
