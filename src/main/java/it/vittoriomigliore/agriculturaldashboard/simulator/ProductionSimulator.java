package it.vittoriomigliore.agriculturaldashboard.simulator;

public class ProductionSimulator {
    private final double averageProduction;

    public ProductionSimulator(double averageProduction) {
        this.averageProduction = averageProduction;
    }

    // Simulate daily production quantity using a Poisson distribution
    public int simulateDailyProduction() {
        return SimulatorUtils.poisson(averageProduction);
    }
}
