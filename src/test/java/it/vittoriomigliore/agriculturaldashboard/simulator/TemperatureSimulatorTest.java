package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemperatureSimulatorTest {
    private TemperatureSimulator temperatureSimulator;

    @BeforeEach
    public void setUp() {
        temperatureSimulator = new TemperatureSimulator();
    }
    @Test
    public void testGaussianDistribution() {
        for (int i = 0; i < 1000; i++) {
            double value = temperatureSimulator.generate();

            // Verifica che la temperatura generata sia entro un range ragionevole
            double averageTemperature = 20.0;
            double standardDeviationTemperature = 5.0;
            assertTrue(value >= averageTemperature - 3 * standardDeviationTemperature,
                    "Temperatura troppo bassa: " + value);
            assertTrue(value <= averageTemperature + 3 * standardDeviationTemperature,
                    "Temperatura troppo alta: " + value);
        }
    }
}
