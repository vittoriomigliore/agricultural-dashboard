package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import java.util.Random;

public class TemperatureSimulator {
    private double dailyMeanTemperature;
    private double dailyStandardDeviation;
    private final Random random;

    // Temperature range for daily means and standard deviations
    private static final double meanMin = 15.0; // Minimum daily mean temperature
    private static final double meanMax = 30.0; // Maximum daily mean temperature
    private static final double stdDevMin = 1.0; // Minimum daily standard deviation
    private static final double stdDevMax = 5.0; // Maximum daily standard deviation

    public TemperatureSimulator() {
        this.random = new Random();
        generateDailyTemperatureParameters(); // Initialize with first day's parameters
    }

    // Generate daily mean and standard deviation for temperature
    public void generateDailyTemperatureParameters() {
        this.dailyMeanTemperature = meanMin + (meanMax - meanMin) * random.nextDouble();
        this.dailyStandardDeviation = stdDevMin + (stdDevMax - stdDevMin) * random.nextDouble();
    }

    // Simulate temperature for the current day (Gaussian distribution)
    public double simulateTemperatureForMinute() {
        return random.nextGaussian() * dailyStandardDeviation + dailyMeanTemperature;
    }

    public double getDailyMeanTemperature() {
        return dailyMeanTemperature;
    }

    public double getDailyStandardDeviation() {
        return dailyStandardDeviation;
    }
}

