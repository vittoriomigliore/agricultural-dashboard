package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CostSimulator {
    private final Random random;
    private double mean; // The average cost
    private double stdDev; // The standard deviation for cost variability

    public CostSimulator() {
        this.random = new Random();
    }

    public void init(double mean, double stdDev) {
        this.mean = mean;
        this.stdDev = stdDev;
    }

    // Simulate daily cost using a normal distribution
    public double simulateDailyCost() {
        return mean + stdDev * random.nextGaussian(); // Using Gaussian distribution
    }
}

