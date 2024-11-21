package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import it.vittoriomigliore.agriculturaldashboard.core.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Service
public class ProductionService {
    ProductionRepository productionRepository;

    @Autowired
    public ProductionService(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    public Production saveProduction(Production production) {
        return productionRepository.save(production);
    }

    public Boolean existsProductionByFieldAndHarvestDate(Field field, LocalDate date) {
        return productionRepository.countByFieldAndHarvestDate(field, date) > 0;
    }

    public Production getProductionByFieldAndHarvestDate(Field field, LocalDate date) {
        return productionRepository.findFirstByFieldAndHarvestDate(field, date);
    }

    public Production getLastProductionByField(Field field) {
        return productionRepository.findFirstByFieldOrderByHarvestDateDesc(field);
    }

    public BigDecimal productionSumByFieldAndDate(Field field, LocalDate date) {
        return productionRepository.sumProductionByFieldAndHarvestDateBetween(field, date, date);
    }

    public BigDecimal productionSumByMonth(Month month) {
        int currentYear = LocalDate.now().getYear();
        Year currentYearObject = Year.of(currentYear);
        LocalDate startDate = LocalDate.of(currentYear, month, 1);
        LocalDate endDate = LocalDate.of(currentYear, month, month.length(currentYearObject.isLeap()));
        return productionRepository.sumProductionByFieldAndHarvestDateBetween(null, startDate, endDate);
    }
}
