package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public abstract class BaseMetricSimulatorTest {

    protected BaseMetricSimulator simulator;

    protected abstract double[] getTestParameters();

    @Test
    public void testSimulationResultNonNegative() {
        simulator.setParameters(getTestParameters());
        Object result = simulator.simulate();

        if (result instanceof Double) {
            double value = (Double) result;
            assertTrue("Value should be greater than or equal to 0", value >= 0);
        }
        if (result instanceof Integer) {
            int value = (Integer) result;
            assertTrue("Value should be non-negative", value >= 0);
        }
    }
}

