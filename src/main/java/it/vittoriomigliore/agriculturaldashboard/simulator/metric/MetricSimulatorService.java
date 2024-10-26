package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.service.CostService;
import it.vittoriomigliore.agriculturaldashboard.core.service.CropService;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MetricSimulatorService {

    private static final double COST_MEAN = 1000.0;
    private static final double COST_STD_DEV = 100.0;
    private final FieldService fieldService;

    CostService costService;
    CropService cropService;
    CostSimulator costSimulator;

    MetricSimulatorService(CostService costService, CropService cropService, FieldService fieldService, CostSimulator costSimulator) {
        this.costService = costService;
        this.cropService = cropService;
        this.fieldService = fieldService;
        this.costSimulator = costSimulator;
        initializeMetrics();
    }

    @Scheduled(cron = "0 0 2 * * ?") // Runs every day at 2:00 AM
    public void initializeMetrics() {
        costSimulator.init(COST_MEAN, COST_STD_DEV);
    }

    @Scheduled(fixedRate = 1800000) // Runs every hour
    public void simulateMetrics() {
        List<Crop> crops = cropService.getAllCrops();
        crops.forEach((crop) -> {
            List<Field> fields = fieldService.getAllFieldsByCrop(crop);
            fields.forEach((field) -> saveSimulatedCost(crop, field));
        });
    }

    private void saveSimulatedCost(Crop crop, Field field) {
        Cost cost = new Cost();
        cost.setDate(LocalDate.now());
        cost.setField(field);
        cost.setCrop(crop);

        BigDecimal amount = BigDecimal.valueOf(costSimulator.simulateDailyCost());
        cost.setAmount(amount);

        costService.saveCost(cost);
    }
}
