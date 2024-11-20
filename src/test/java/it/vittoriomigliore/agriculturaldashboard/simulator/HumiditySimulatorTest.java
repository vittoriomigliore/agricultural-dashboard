package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HumiditySimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private HumiditySimulator humiditySimulator;

    @BeforeEach
    public void setup() {
        simulator = humiditySimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{70.0, 10.0}; // Mean, Std Dev
    }
}
