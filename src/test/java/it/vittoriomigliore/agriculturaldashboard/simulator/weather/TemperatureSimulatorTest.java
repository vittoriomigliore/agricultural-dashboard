package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TemperatureSimulatorTest {
    private TemperatureSimulator temperatureSimulator;

    @BeforeEach
    public void setUp() {
        temperatureSimulator = new TemperatureSimulator();
    }

    @Test
    public void testGenerateDailyTemperatureParameters() {
        temperatureSimulator.generateDailyTemperatureParameters();

        double dailyMean = temperatureSimulator.getDailyMeanTemperature();
        double dailyStdDev = temperatureSimulator.getDailyStandardDeviation();

        // Verify mean is within the expected range
        assertTrue("Daily mean temperature should be between 15 and 30",
                dailyMean >= 15.0 && dailyMean <= 30.0);

        // Verify standard deviation is within the expected range
        assertTrue("Daily standard deviation should be between 1 and 5",
                dailyStdDev >= 1.0 && dailyStdDev <= 5.0);
    }

    // Test to simulate multiple temperature values for a given day
    @Test
    public void testSimulateTemperatureForMinute() {
        // Generate parameters for the day
        temperatureSimulator.generateDailyTemperatureParameters();
        double dailyMean = temperatureSimulator.getDailyMeanTemperature();
        double dailyStdDev = temperatureSimulator.getDailyStandardDeviation();

        for (int i = 0; i < 1000; i++) { // Simulate 1000 temperature readings
            double temperature = temperatureSimulator.simulateTemperatureForMinute();

            // Verify that the temperature is reasonably within range
            assertTrue("Temperature should be within 4 standard deviations of the mean",
                    Math.abs(temperature - dailyMean) <= 4 * dailyStdDev);
        }
    }

    // Test to verify that mean temperature across multiple samples is close to the daily mean
    @Test
    public void testTemperatureDistributionOverDay() {
        temperatureSimulator.generateDailyTemperatureParameters();
        double dailyMean = temperatureSimulator.getDailyMeanTemperature();

        double sum = 0;
        int numberOfSamples = 1000; // Simulate 1000 readings for the day

        for (int i = 0; i < numberOfSamples; i++) {
            sum += temperatureSimulator.simulateTemperatureForMinute();
        }

        double averageTemperature = sum / numberOfSamples;

        // Check that the average temperature is close to the daily mean
        assertTrue("Average temperature should be close to the daily mean",
                Math.abs(averageTemperature - dailyMean) < 1.0); // Within 1°C
    }
}
