package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class HumiditySimulatorTest {

    private HumiditySimulator humiditySimulator;

    @BeforeEach
    public void setUp() {
        // Setting alpha = 2 and beta = 5 to simulate typical daily humidity profiles
        humiditySimulator = new HumiditySimulator(2.0, 5.0);
    }

    // Test to check if daily mean humidity is generated correctly
    @Test
    public void testGenerateDailyMeanHumidity() {
        humiditySimulator.generateDailyMeanHumidity();

        double dailyMeanHumidity = humiditySimulator.getDailyMeanHumidity();

        // Verify that the daily mean humidity is between 0 and 100
        assertTrue("Daily mean humidity should be between 0 and 100",
                dailyMeanHumidity >= 0.0 && dailyMeanHumidity <= 100.0);
    }

    // Test to simulate humidity for a given minute
    @Test
    public void testSimulateHumidityForMinute() {
        humiditySimulator.generateDailyMeanHumidity();

        for (int i = 0; i < 1000; i++) { // Simulate 1000 minutes
            double humidity = humiditySimulator.simulateHumidityForMinute();
            // Ensure humidity stays within 0 and 100% range
            assertTrue("Humidity should be between 0 and 100%",
                    humidity >= 0.0 && humidity <= 100.0);
        }
    }

    // Test to verify humidity fluctuations remain within a reasonable range
    @Test
    public void testHumidityFluctuations() {
        humiditySimulator.generateDailyMeanHumidity();
        double dailyMeanHumidity = humiditySimulator.getDailyMeanHumidity();

        for (int i = 0; i < 1000; i++) {
            double humidity = humiditySimulator.simulateHumidityForMinute();
            // Check that humidity fluctuations are reasonable (within ±20% of the mean)
            assertTrue("Humidity should not deviate too much from the daily mean",
                    Math.abs(humidity - dailyMeanHumidity) <= 20.0);
        }
    }

    // Test the distribution of generated humidity values
    @Test
    public void testHumidityDistribution() {
        int samples = 1000;
        double sum = 0.0;

        for (int i = 0; i < samples; i++) {
            humiditySimulator.generateDailyMeanHumidity();
            sum += humiditySimulator.getDailyMeanHumidity();
        }

        // Verify the average daily mean humidity is within a reasonable range for alpha=2, beta=5
        double averageHumidity = sum / samples;
        assertTrue("Average daily humidity should be between 25 and 40",
                averageHumidity >= 25.0 && averageHumidity <= 40.0);
    }
}

