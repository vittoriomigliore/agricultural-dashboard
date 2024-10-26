package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.simulator.SimulatorUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductionSimulator {
    private double averageProduction;

    public ProductionSimulator() {
    }

    public void init(double averageProduction) {
        this.averageProduction = averageProduction;
    }

    // Simulate daily production quantity using a Poisson distribution
    public int simulateDailyProduction() {
        return SimulatorUtils.poisson(averageProduction);
    }
}
