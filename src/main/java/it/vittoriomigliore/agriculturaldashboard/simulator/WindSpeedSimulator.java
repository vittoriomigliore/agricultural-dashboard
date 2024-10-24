package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class WindSpeedSimulator {
    private final Random random;
    private final double shape; // Weibull shape parameter (k)
    private final double scale; // Weibull scale parameter (λ)
    private double dailyMeanWindSpeed;

    // Constructor
    public WindSpeedSimulator(double shape, double scale) {
        this.random = new Random();
        this.shape = shape;
        this.scale = scale;
        generateDailyMeanWindSpeed(); // Initialize with the first day's wind speed
    }

    // Generate daily mean wind speed using Weibull distribution
    public void generateDailyMeanWindSpeed() {
        this.dailyMeanWindSpeed = generateWeibullSample(shape, scale);
    }

    // Simulate wind speed for a given minute, adding small fluctuation
    public double simulateWindSpeedForMinute() {
        // Add some fluctuation based on Gaussian distribution
        double fluctuation = random.nextGaussian() * 0.5; // Small fluctuation (std dev of 0.5)
        double windSpeed = dailyMeanWindSpeed + fluctuation;

        // Ensure the wind speed is non-negative
        return Math.max(0.0, windSpeed);
    }

    // Generate a sample from a Weibull distribution
    private double generateWeibullSample(double k, double lambda) {
        // Inverse transform sampling for Weibull distribution
        double u = random.nextDouble();
        return lambda * Math.pow(-Math.log(1 - u), 1 / k);
    }

    // Getters for testing
    public double getDailyMeanWindSpeed() {
        return dailyMeanWindSpeed;
    }
}

