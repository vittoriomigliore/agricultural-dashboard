package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RainSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private RainSimulator rainSimulator;

    @BeforeEach
    public void setup() {
        simulator = rainSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{1 / 0.5}; // Lambda
    }
}
