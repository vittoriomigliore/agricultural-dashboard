package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulatorTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductionSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private ProductionSimulator productionSimulator;

    @BeforeEach
    public void setup() {
        simulator = productionSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{1000, 100};
    }
}
