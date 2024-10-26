package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IrrigationSimulatorTest {

    public static final double MEAN = 200.0;
    public static final double STD_DEV = 20.0;
    private IrrigationSimulator irrigationSimulator;

    @BeforeEach
    public void setUp() {
        // Setting an average daily irrigation of 200 liters per hectare with a standard deviation of 20
        irrigationSimulator = new IrrigationSimulator();
        irrigationSimulator.init(MEAN, STD_DEV);
    }

    // Test to verify the irrigation distribution is reasonable
    @Test
    public void testIrrigationDistribution() {
        double totalIrrigation = 0;
        int samples = 1000; // Number of days we're simulating

        // Simulate daily irrigation for 1000 days
        for (int i = 0; i < samples; i++) {
            totalIrrigation += irrigationSimulator.simulateDailyIrrigation();
        }

        // Calculate the average irrigation over the 1000 days
        double averageIrrigation = totalIrrigation / samples;

        // The expected mean is 200 liters per hectare
        double expectedMean = 200.0;

        // Standard deviation is 20
        double stdDev = 20.0;

        // Standard error = standard deviation / sqrt(number of samples)
        double standardError = stdDev / Math.sqrt(samples);

        // Allow the average to vary within 3 standard errors (99.7% confidence interval)
        double tolerance = 3 * standardError;

        // Assert that the average irrigation is within the expected range
        assertEquals(expectedMean, averageIrrigation, tolerance);
    }
}

