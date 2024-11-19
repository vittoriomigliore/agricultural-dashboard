package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaleSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private SaleSimulator saleSimulator;

    @BeforeEach
    public void setup() {
        simulator = saleSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{2.0};
    }
}
