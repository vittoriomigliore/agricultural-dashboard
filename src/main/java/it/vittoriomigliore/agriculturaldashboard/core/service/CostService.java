package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.repository.CostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

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

    public BigDecimal costsSumToToday() {
        LocalDate date = LocalDate.now();
        return costRepository.sumCostsByDateBefore(date);
    }

    public Integer costsPercentageChange() {
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);

        BigDecimal sumToday = costsSumToToday();
        BigDecimal sumBefore = costRepository.sumCostsByDateBefore(tenDaysAgo);

        return calculatePercentageChange(sumBefore, sumToday);
    }

    public static Integer calculatePercentageChange(BigDecimal oldValue, BigDecimal newValue) {
        if (oldValue.compareTo(BigDecimal.ZERO) == 0) {
            return newValue.compareTo(BigDecimal.ZERO) > 0 ? 100 : 0;
        }

        BigDecimal difference = newValue.subtract(oldValue);

        BigDecimal percentageChange = difference
                .divide(oldValue, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return percentageChange.setScale(0, RoundingMode.HALF_UP).intValue();
    }

}
