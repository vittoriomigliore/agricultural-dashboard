package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.core.entity.*;
import it.vittoriomigliore.agriculturaldashboard.core.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricSimulatorService {

    private static final double COST_MEAN = 1000.0;
    private static final double COST_STD_DEV = 100.0;
    private static final double IRRIGATION_MEAN = 200.0;
    private static final double IRRIGATION_STD_DEV = 20.0;
    private static final double PRODUCTION_AVG = 50.0;
    private static final double SALES_RATE = 10.0;

    private final FieldService fieldService;

    private final CostService costService;
    private final IrrigationService irrigationService;
    private final ProductionService productionService;
    private final SaleService saleService;

    private final CostSimulator costSimulator;
    private final IrrigationSimulator irrigationSimulator;
    private final ProductionSimulator productionSimulator;
    private final SalesSimulator salesSimulator;

    MetricSimulatorService(FieldService fieldService,
                           CostService costService, IrrigationService irrigationService, ProductionService productionService, SaleService saleService,
                           CostSimulator costSimulator, IrrigationSimulator irrigationSimulator, ProductionSimulator productionSimulator, SalesSimulator salesSimulator) {
        this.fieldService = fieldService;

        this.costService = costService;
        this.irrigationService = irrigationService;
        this.productionService = productionService;
        this.saleService = saleService;

        this.costSimulator = costSimulator;
        this.irrigationSimulator = irrigationSimulator;
        this.productionSimulator = productionSimulator;
        this.salesSimulator = salesSimulator;

        initializeMetrics();
    }

    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2:00 AM
    public void initializeMetrics() {
        costSimulator.init(COST_MEAN, COST_STD_DEV);
        irrigationSimulator.init(IRRIGATION_MEAN, IRRIGATION_STD_DEV);
        productionSimulator.init(PRODUCTION_AVG);
        salesSimulator.init(SALES_RATE);
    }

    @Scheduled(fixedRateString = "${simulator.irrigation.rate}")
    public void simulateIrrigation() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(this::saveSimulatedIrrigation);
    }

    @Scheduled(fixedRateString = "${simulator.production.rate}")
    public void simulateProduction() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(this::saveSimulatedProduction);
    }

    @Scheduled(fixedRateString = "${simulator.sales.rate}")
    public void simulateSales() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach((field) -> {
            Production production = productionService.getLastProductionByField(field);
            saveSimulatedSale(production);
        });
    }

    @Scheduled(fixedRateString = "${simulator.costs.rate}")
    public void simulateCost() {
        List<Field> fields = fieldService.getAllFields();
        fields.forEach(this::saveSimulatedCost);
    }

    private void saveSimulatedCost(Field field) {
        Cost cost = new Cost();
        cost.setDate(LocalDate.now());
        cost.setField(field);

        BigDecimal amount = BigDecimal.valueOf(costSimulator.simulateDailyCost());
        cost.setAmount(amount);

        costService.saveCost(cost);
    }

    private void saveSimulatedIrrigation(Field field) {
        Irrigation irrigation = new Irrigation();
        irrigation.setField(field);
        irrigation.setDateTime(LocalDateTime.now());

        BigDecimal amount = BigDecimal.valueOf(irrigationSimulator.simulateDailyIrrigation());
        irrigation.setAmountUsed(amount);

        irrigationService.saveIrrigation(irrigation);
    }

    private void saveSimulatedProduction(Field field) {
        Production production = new Production();
        production.setField(field);
        production.setHarvestDate(LocalDate.now());

        BigDecimal quantity = BigDecimal.valueOf(productionSimulator.simulateDailyProduction());
        production.setQuantity(quantity);

        productionService.saveProduction(production);
    }

    private void saveSimulatedSale(Production production) {
        Sale sale = new Sale();
        sale.setProduction(production);
        sale.setDate(LocalDate.now());

        BigDecimal amount = BigDecimal.valueOf(salesSimulator.simulateDailySales());
        sale.setSalePricePerUnit(amount);
        BigDecimal quantity = BigDecimal.valueOf(salesSimulator.simulateDailyQuantitySales());
        sale.setQuantitySold(quantity);

        saleService.saveProduction(sale);
    }
}
