package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class WindSpeedSimulator {
    private final Random random;
    private double dailyMeanWindSpeed;

    // Constructor
    public WindSpeedSimulator() {
        this.random = new Random();
    }

    // Generate daily mean wind speed using Weibull distribution
    public void generateDailyMeanWindSpeed(double shape, double scale) {
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

