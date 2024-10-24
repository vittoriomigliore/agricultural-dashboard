package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class SimulatorUtils {
    static int poisson(double lambda) {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;
        do {
            k++;
            Random random = new Random();
            p *= random.nextDouble();
        } while (p > L);
        return k - 1;
    }
}
