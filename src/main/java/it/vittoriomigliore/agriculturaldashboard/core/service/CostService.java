package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.repository.CostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Service
public class CostService {
    private static final Logger log = LoggerFactory.getLogger(CostService.class);
    CostRepository costRepository;

    @Autowired
    public CostService(CostRepository costRepository) {
        this.costRepository = costRepository;
    }

    public Cost saveCost(Cost cost) {
        return costRepository.save(cost);
    }

    public BigDecimal costsSum() {
        LocalDate date = LocalDate.now();
        return costRepository.sumCostsByDateBefore(date);
    }

    public BigDecimal costsSumTo10DaysAgo() {
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        return costRepository.sumCostsByDateBefore(tenDaysAgo);
    }

    public BigDecimal costsSumByFieldAndMonth(Field field, Month month) {
        int currentYear = LocalDate.now().getYear();
        Year currentYearObject = Year.of(currentYear);
        LocalDate startDate = LocalDate.of(currentYear, month, 1);
        LocalDate endDate = LocalDate.of(currentYear, month, month.length(currentYearObject.isLeap()));
        return costRepository.sumCostsByFieldAndDateBetween(field, startDate, endDate);
    }

}
