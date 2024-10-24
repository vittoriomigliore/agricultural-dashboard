package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductionSimulatorTest {

    private ProductionSimulator productionSimulator;

    @BeforeEach
    public void setUp() {
        // Setting an average daily production of 50 units
        productionSimulator = new ProductionSimulator(50.0);
    }

    // Test to verify the production distribution is reasonable
    @Test
    public void testProductionDistribution() {
        int totalProduction = 0;
        int samples = 1000; // Number of days we're simulating

        // Simulate daily production for 1000 days
        for (int i = 0; i < samples; i++) {
            totalProduction += productionSimulator.simulateDailyProduction();
        }

        // Calculate the average production over the 1000 days
        double averageProduction = totalProduction / (double) samples;

        // The expected mean is 50
        double expectedMean = 50.0;

        // The standard deviation for a Poisson distribution is the square root of the mean
        double standardDeviation = Math.sqrt(expectedMean);

        // Standard error = standard deviation / sqrt(number of samples)
        double standardError = standardDeviation / Math.sqrt(samples);

        // Allow the average to vary within 3 standard errors (99.7% confidence interval)
        double tolerance = 3 * standardError;

        // Assert that the average production is within the expected range
        assertEquals(expectedMean, averageProduction, tolerance);
    }
}
