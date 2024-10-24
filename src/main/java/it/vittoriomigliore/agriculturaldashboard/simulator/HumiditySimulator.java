package it.vittoriomigliore.agriculturaldashboard.simulator;

import java.util.Random;

public class HumiditySimulator {
    private final Random random;
    private final double alpha; // Shape parameter for the beta distribution
    private final double beta;  // Shape parameter for the beta distribution
    // Humidity range (0 to 100%)
    private final double minHumidity = 0.0;
    private final double maxHumidity = 100.0;
    private double dailyMeanHumidity;

    // Constructor to initialize parameters for the beta distribution
    public HumiditySimulator(double alpha, double beta) {
        this.random = new Random();
        this.alpha = alpha;
        this.beta = beta;
        generateDailyMeanHumidity(); // Initialize with the first day's humidity
    }

    // Simulate daily mean humidity using Beta distribution
    public void generateDailyMeanHumidity() {
        // Beta-distributed random number between 0 and 1
        double betaSample = generateBetaSample(alpha, beta);
        // Scale it to the humidity range [0, 100]
        this.dailyMeanHumidity = minHumidity + betaSample * (maxHumidity - minHumidity);
    }

    // Simulate humidity for a given minute, adding some random fluctuation around the daily mean
    public double simulateHumidityForMinute() {
        // Adding a random fluctuation (e.g., ±5% of the daily mean)
        double fluctuation = random.nextGaussian() * 5.0; // Std dev of 5% for fluctuations
        double humidity = dailyMeanHumidity + fluctuation;

        // Ensure the value stays within [0, 100]
        return Math.max(minHumidity, Math.min(maxHumidity, humidity));
    }

    // Generate a sample from a beta distribution
    private double generateBetaSample(double alpha, double beta) {
        // Using the method of generating a beta sample from two gamma distributions
        double x = randomGamma(alpha);
        double y = randomGamma(beta);
        return x / (x + y); // Beta-distributed random number between 0 and 1
    }

    // Gamma distribution sampler using Marsaglia-Tsang method for alpha > 1
    private double randomGamma(double shape) {
        if (shape < 1.0) {
            shape += 1.0;
        }
        double d = shape - 1.0 / 3.0;
        double c = 1.0 / Math.sqrt(9.0 * d);
        double v;
        while (true) {
            double x = random.nextGaussian();
            v = 1.0 + c * x;
            if (v > 0) {
                v = v * v * v;
                double u = random.nextDouble();
                if (u < 1.0 - 0.0331 * (x * x * x * x)) {
                    break;
                }
                if (Math.log(u) < 0.5 * x * x + d * (1.0 - v + Math.log(v))) {
                    break;
                }
            }
        }
        return d * v;
    }

    // Getters for testing
    public double getDailyMeanHumidity() {
        return dailyMeanHumidity;
    }
}

