package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CostRepository extends JpaRepository<Cost, Integer> {

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Cost c WHERE c.date <= :date")
    BigDecimal sumCostsByDateBefore(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Cost c WHERE c.crop = :crop AND c.date BETWEEN :startDate AND :endDate")
    BigDecimal sumCostsByCropAndDateBetween(@Param("crop") Crop crop,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

}
