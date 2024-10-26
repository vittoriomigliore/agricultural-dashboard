package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IrrigationSimulator {
    private double meanIrrigation; // The average irrigation volume per day (liters per hectare)
    private double stdDevIrrigation; // The standard deviation for irrigation variability
    private final Random random;

    public IrrigationSimulator() {
        this.random = new Random();
    }

    public void init(double meanIrrigation, double stdDevIrrigation) {
        this.meanIrrigation = meanIrrigation;
        this.stdDevIrrigation = stdDevIrrigation;
    }

    // Simulate daily irrigation using a normal distribution
    public double simulateDailyIrrigation() {
        // Generate a random value from a normal distribution
        return meanIrrigation + stdDevIrrigation * random.nextGaussian();
    }
}
