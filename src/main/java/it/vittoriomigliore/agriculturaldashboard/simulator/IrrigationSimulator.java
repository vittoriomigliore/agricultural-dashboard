package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class IrrigationSimulator {
    private final double meanIrrigation; // The average irrigation volume per day (liters per hectare)
    private final double stdDevIrrigation; // The standard deviation for irrigation variability
    private final Random random;

    public IrrigationSimulator(double meanIrrigation, double stdDevIrrigation) {
        this.meanIrrigation = meanIrrigation;
        this.stdDevIrrigation = stdDevIrrigation;
        this.random = new Random();
    }

    // Simulate daily irrigation using a normal distribution
    public double simulateDailyIrrigation() {
        // Generate a random value from a normal distribution
        return meanIrrigation + stdDevIrrigation * random.nextGaussian();
    }
}
