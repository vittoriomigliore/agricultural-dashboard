package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import it.vittoriomigliore.agriculturaldashboard.core.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

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

    public List<Production> getAllProductionsByCropAndMonth(Crop crop, Month month) {
        int currentYear = LocalDate.now().getYear();
        Year currentYearObject = Year.of(currentYear);
        LocalDate startDate = LocalDate.of(currentYear, month, 1);
        LocalDate endDate = LocalDate.of(currentYear, month, month.length(currentYearObject.isLeap()));
        return productionRepository.findAllByCropAndHarvestDateBetween(crop, startDate, endDate);
    }
}
