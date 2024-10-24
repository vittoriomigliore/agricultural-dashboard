package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


public class RainSimulatorTest {
    private RainSimulator simulator;

    @BeforeEach
    public void setUp() {
        simulator = new RainSimulator();
    }

    @Test
    public void testSimulatePrecipitationPerMinute() {
        simulator.generateDailyRainProbability();

        double precipitation = simulator.simulatePrecipitationPerMinute();

        // The precipitation must be >= 0 (it might not rain)
        assertTrue("Precipitation must be >= 0", precipitation >= 0.0);

        // If it rains, the value must be between min and max
        if (precipitation > 0) {
            assertTrue("Precipitation must be <= 5.0 mm", precipitation <= 5.0);
        }
    }

    @Test
    public void testSimulateNoPrecipitation() {
        // Set daily rain probability to 0 (no rain)
        simulator.setDailyRainProbability(0.0);

        // Simulate precipitation for the minute
        double precipitation = simulator.simulatePrecipitationPerMinute();

        // Check that there is no precipitation
        assertEquals("There should be no precipitation when probability is 0", 0.0, precipitation);
    }

    // Test to verify simulation with rain always present
    @Test
    public void testSimulateAlwaysRain() {
        // Set daily rain probability to 1 (always rain)
        simulator.setDailyRainProbability(1.0);

        // Simulate precipitation for the minute
        double precipitation = simulator.simulatePrecipitationPerMinute();

        // Check that there is precipitation > 0
        assertTrue("There must be precipitation when probability is 1", precipitation > 0);
        assertTrue("Precipitation must be <= 5.0 mm", precipitation <= 5.0);
    }
}
