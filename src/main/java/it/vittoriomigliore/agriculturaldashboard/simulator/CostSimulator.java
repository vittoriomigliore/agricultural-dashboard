package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class CostSimulator {
    private final double mean; // The average cost
    private final double stdDev; // The standard deviation for cost variability
    private final Random random;

    public CostSimulator(double mean, double stdDev) {
        this.mean = mean;
        this.stdDev = stdDev;
        this.random = new Random();
    }

    // Simulate daily cost using a normal distribution
    public double simulateDailyCost() {
        return mean + stdDev * random.nextGaussian(); // Using Gaussian distribution
    }
}

