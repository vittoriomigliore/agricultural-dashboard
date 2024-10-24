package it.vittoriomigliore.agriculturaldashboard.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RainSimulatorTest {
    private RainSimulator rainSimulator;

    @BeforeEach
    public void setUp() {
        rainSimulator = new RainSimulator();
    }

    @Test
    void testPoissonDistribution() {
        for (int i = 0; i < 1000; i++) {
            int value = rainSimulator.generate();

            // Verifica che il numero di giorni di pioggia sia >= 0 (la distribuzione di Poisson non genera valori negativi)
            assertTrue(value >= 0, "Numero di giorni di pioggia non può essere negativo: " + value);

            // Verifica che il numero di giorni di pioggia sia ragionevole (es. non superiore a 30 in un mese)
            assertTrue(value <= 30, "Numero di giorni di pioggia irrealistico: " + value);
        }
    }
}
