package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class DistributionUtils {
    private static final Random random = new Random();

    // Gaussian Distribution: Mean (mu) and Standard Deviation (sigma)
    public static double generateGaussian(double mu, double sigma) {
        return mu + random.nextGaussian() * sigma;
    }

    // Exponential Distribution: Mean (1/lambda)
    public static double generateExponential(double lambda) {
        return -Math.log(1 - random.nextDouble()) / lambda;
    }

    // Poisson Distribution: Lambda (average rate of occurrence)
    public static int generatePoisson(double lambda) {
        int k = 0;
        double p = 1.0;
        double L = Math.exp(-lambda);
        do {
            k++;
            p *= random.nextDouble();
        } while (p > L);
        return k - 1;
    }
}
