package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostSimulatorTest {

    public static final double MEAN = 1000.0;
    public static final double STD_DEV = 100.0;
    private CostSimulator costSimulator;

    @BeforeEach
    public void setUp() {
        // Setting an average cost of 1000 with a standard deviation of 100
        costSimulator = new CostSimulator();
        costSimulator.init(MEAN, STD_DEV);
    }

    // Test to verify the costs distribution is reasonable
    @Test
    public void testCostsDistribution() {
        int samples = 1000; // Number of days we're simulating
        double totalCosts = 0;

        // Simulate daily costs for 1000 days
        for (int i = 0; i < samples; i++) {
            totalCosts += costSimulator.simulateDailyCost();
        }

        // Calculate the average cost over the 1000 days
        double averageCost = totalCosts / samples;

        // The expected mean is 1000
        double expectedMean = 1000.0;

        // Standard deviation is 100
        double stdDev = 100.0;

        // Standard error = standard deviation / sqrt(number of samples)
        double standardError = stdDev / Math.sqrt(samples);

        // Allow the average to vary within 3 standard errors (99.7% confidence interval)
        double tolerance = 3 * standardError;

        // Assert that the average cost is within the expected range
        assertEquals(expectedMean, averageCost, tolerance);
    }
}
