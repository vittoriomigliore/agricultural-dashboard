package it.vittoriomigliore.agriculturaldashboard.core.repository;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query("SELECT COALESCE(SUM(c.quantitySold * c.salePricePerUnit), 0) FROM Sale c WHERE c.date <= :date")
    BigDecimal sumSalesByDateBefore(@Param("date") LocalDate date);


    @Query("SELECT COALESCE(SUM(c.quantitySold * c.salePricePerUnit), 0) FROM Sale c WHERE c.date = :date")
    BigDecimal sumSalesByDate(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(c.quantitySold * c.salePricePerUnit), 0) FROM Sale c WHERE c.date BETWEEN :startDate AND :endDate")
    BigDecimal sumSalesByDateBetween(LocalDate startDate, LocalDate endDate);
}
