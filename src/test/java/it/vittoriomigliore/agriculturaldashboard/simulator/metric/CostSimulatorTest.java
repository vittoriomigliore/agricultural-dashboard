package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulatorTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CostSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private CostSimulator costSimulator;

    @BeforeEach
    public void setup() {
        simulator = costSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{5000, 500};
    }
}
