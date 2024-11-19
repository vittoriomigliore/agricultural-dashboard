package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulatorTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WindSpeedSimulatorTest extends BaseMetricSimulatorTest {

    @Autowired
    private WindSpeedSimulator windSpeedSimulator;

    @BeforeEach
    void setup() {
        simulator = windSpeedSimulator;
    }

    @Override
    protected double[] getTestParameters() {
        return new double[]{15.0, 2.0};
    }
}
