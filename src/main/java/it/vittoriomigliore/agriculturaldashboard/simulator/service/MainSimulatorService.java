package it.vittoriomigliore.agriculturaldashboard.simulator.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.*;
import it.vittoriomigliore.agriculturaldashboard.core.service.*;
import it.vittoriomigliore.agriculturaldashboard.simulator.CostSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.IrrigationSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.ProductionSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.SaleSimulator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MainSimulatorService {

    private final FieldService fieldService;
    private final CostService costService;
    private final IrrigationService irrigationService;
    private final ProductionService productionService;
    private final SaleService saleService;
    private final CostSimulator costSimulator;
    private final IrrigationSimulator irrigationSimulator;
    private final ProductionSimulator productionSimulator;
    private final SaleSimulator saleSimulator;

    MainSimulatorService(FieldService fieldService,
                         CostService costService,
                         IrrigationService irrigationService,
                         ProductionService productionService,
                         SaleService saleService,
                         CostSimulator costSimulator,
                         IrrigationSimulator irrigationSimulator,
                         ProductionSimulator productionSimulator,
                         SaleSimulator saleSimulator) {
        this.fieldService = fieldService;
        this.costService = costService;
        this.irrigationService = irrigationService;
        this.productionService = productionService;
        this.saleService = saleService;
        this.costSimulator = costSimulator;
        this.irrigationSimulator = irrigationSimulator;
        this.productionSimulator = productionSimulator;
        this.saleSimulator = saleSimulator;
        initializeSimulators();
    }

    public void initializeSimulators() {
        productionSimulator.setParameters(1000, 100);
        saleSimulator.setParameters(2);
        costSimulator.setParameters(5000, 500);
        irrigationSimulator.setParameters(20);
    }

    @Scheduled(fixedRateString = "${simulator.production.rate}")
    public void simulateProduction() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(field -> {
            LocalDate current = LocalDate.now();
            if (productionService.existsProductionByFieldAndHarvestDate(field, current)) {
                BigDecimal value = BigDecimal.valueOf(productionSimulator.simulate());
                Production production = new Production();
                production.setField(field);
                production.setHarvestDate(current);
                production.setQuantity(value);
                productionService.saveProduction(production);
            }
        });
    }

    @Scheduled(fixedRateString = "${simulator.sales.rate}")
    public void simulateSales() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(field -> {
            LocalDate current = LocalDate.now();
            Production production = productionService.getProductionByFieldAndHarvestDate(field, current);
            Sale sale = new Sale();
            sale.setProduction(production);
            sale.setDate(LocalDate.now());
            sale.setSalePricePerUnit(BigDecimal.valueOf(10));
            BigDecimal quantity = BigDecimal.valueOf(saleSimulator.simulate());
            sale.setQuantitySold(quantity);

            saleService.saveProduction(sale);
        });
    }

    @Scheduled(fixedRateString = "${simulator.costs.rate}")
    public void simulateCost() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(field -> {
            Cost cost = new Cost();
            cost.setDate(LocalDate.now());
            cost.setField(field);

            BigDecimal amount = BigDecimal.valueOf(costSimulator.simulate());
            cost.setAmount(amount);

            costService.saveCost(cost);
        });
    }

    @Scheduled(fixedRateString = "${simulator.irrigation.rate}")
    public void simulateIrrigation() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(field -> {
            Irrigation irrigation = new Irrigation();
            irrigation.setField(field);
            irrigation.setDateTime(LocalDateTime.now());

            BigDecimal amount = BigDecimal.valueOf(irrigationSimulator.simulate());
            irrigation.setAmountUsed(amount);

            irrigationService.saveIrrigation(irrigation);
        });
    }
}
