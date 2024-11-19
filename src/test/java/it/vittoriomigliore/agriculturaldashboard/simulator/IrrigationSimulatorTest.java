package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IrrigationSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private IrrigationSimulator irrigationSimulator;

    @BeforeEach
    public void setup() {
        simulator = irrigationSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{20};
    }
}
