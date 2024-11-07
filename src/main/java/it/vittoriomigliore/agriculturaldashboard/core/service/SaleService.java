package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Sale;
import it.vittoriomigliore.agriculturaldashboard.core.repository.SaleRepository;
import it.vittoriomigliore.agriculturaldashboard.core.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale saveProduction(Sale sale) {
        return saleRepository.save(sale);
    }

    public BigDecimal salesSum() {
        LocalDate date = LocalDate.now();
        return saleRepository.sumSalesByDateBefore(date);
    }

    public BigDecimal salesSumTo10DaysAgo() {
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        return saleRepository.sumSalesByDateBefore(tenDaysAgo);
    }

    public BigDecimal todaySalesSum() {
        return saleRepository.sumSalesByDate(LocalDate.now());
    }

    public BigDecimal yesterdaySalesSum() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return saleRepository.sumSalesByDate(yesterday);
    }
}
