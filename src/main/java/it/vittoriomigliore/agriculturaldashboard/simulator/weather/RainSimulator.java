package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import java.util.Random;

public class RainSimulator {
    private double dailyRainProbability; // Probability of rain for the day
    private final Random random;

    // Precipitation range in mm (for example, 0 to 5 mm per minute)
    private static final double minPrecipitation = 0.1; // Minimum precipitation
    private static final double maxPrecipitation = 5.0; // Maximum precipitation

    public RainSimulator() {
        this.random = new Random();
        generateDailyRainProbability(); // Initialize with the first day's rain probability
    }

    // Generate a random daily probability of rain
    public void generateDailyRainProbability() {
        this.dailyRainProbability = random.nextDouble(); // Generates a probability between 0.0 and 1.0
    }

    // Simulate precipitation for the current minute
    public double simulatePrecipitationPerMinute() {
        // Check if it rains based on the daily probability
        if (random.nextDouble() <= dailyRainProbability) {
            // Generate precipitation in the range [minPrecipitation, maxPrecipitation]
            return minPrecipitation + (maxPrecipitation - minPrecipitation) * random.nextDouble();
        }
        return 0.0; // No precipitation if it doesn't rain
    }

    public void setDailyRainProbability(double probability) {
        this.dailyRainProbability = probability;
    }
}

