package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TemperatureSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private TemperatureSimulator temperatureSimulator;

    @BeforeEach
    void setup() {
        simulator = temperatureSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{10, 3};
    }
}
