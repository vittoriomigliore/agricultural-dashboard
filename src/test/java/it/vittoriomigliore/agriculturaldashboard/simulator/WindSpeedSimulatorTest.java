package it.vittoriomigliore.agriculturaldashboard.simulator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class WindSpeedSimulatorTest {

    private WindSpeedSimulator windSpeedSimulator;

    @BeforeEach
    public void setUp() {
        // Setting shape = 2 and scale = 10 to simulate typical wind speed conditions
        windSpeedSimulator = new WindSpeedSimulator(2.0, 10.0);
    }

    // Test to check if daily mean wind speed is generated correctly
    @Test
    public void testGenerateDailyMeanWindSpeed() {
        windSpeedSimulator.generateDailyMeanWindSpeed();

        double dailyMeanWindSpeed = windSpeedSimulator.getDailyMeanWindSpeed();

        // Verify that the daily mean wind speed is non-negative
        assertTrue("Daily mean wind speed should be non-negative",
                dailyMeanWindSpeed >= 0.0);
    }

    // Test to simulate wind speed for a given minute
    @Test
    public void testSimulateWindSpeedForMinute() {
        windSpeedSimulator.generateDailyMeanWindSpeed();

        for (int i = 0; i < 1000; i++) { // Simulate 1000 minutes
            double windSpeed = windSpeedSimulator.simulateWindSpeedForMinute();
            // Ensure wind speed is non-negative
            assertTrue("Wind speed should be non-negative", windSpeed >= 0.0);
        }
    }

    // Test to verify wind speed fluctuations remain within a reasonable range
    @Test
    public void testWindSpeedFluctuations() {
        windSpeedSimulator.generateDailyMeanWindSpeed();
        double dailyMeanWindSpeed = windSpeedSimulator.getDailyMeanWindSpeed();

        for (int i = 0; i < 1000; i++) {
            double windSpeed = windSpeedSimulator.simulateWindSpeedForMinute();
            // Check that wind speed fluctuations are reasonable (within ±5 units of the mean)
            assertTrue("Wind speed should not deviate too much from the daily mean",
                    Math.abs(windSpeed - dailyMeanWindSpeed) <= 5.0);
        }
    }

    // Test the distribution of generated wind speed values
    @Test
    public void testWindSpeedDistribution() {
        int samples = 1000;
        double sum = 0.0;

        for (int i = 0; i < samples; i++) {
            windSpeedSimulator.generateDailyMeanWindSpeed();
            sum += windSpeedSimulator.getDailyMeanWindSpeed();
        }

        // Verify the average daily mean wind speed is within a reasonable range for shape=2, scale=10
        double averageWindSpeed = sum / samples;
        assertTrue("Average daily wind speed should be between 7 and 13",
                averageWindSpeed >= 7.0 && averageWindSpeed <= 13.0);
    }
}

