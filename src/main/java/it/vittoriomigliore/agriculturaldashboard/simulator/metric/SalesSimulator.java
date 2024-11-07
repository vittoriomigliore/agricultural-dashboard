package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.simulator.SimulatorUtils;
import org.springframework.stereotype.Component;

@Component
public class SalesSimulator {
    private double dailySalesRate; // Average number of sales per day (λ)

    // Constructor
    public SalesSimulator() {
    }

    public void init(double initialDailySalesRate) {
        this.dailySalesRate = initialDailySalesRate;
    }

    public int simulateDailyQuantitySales() {
        return (int) (Math.random() * 11);
    }

    // Simulate daily sales using a Poisson distribution
    public int simulateDailySales() {
        return SimulatorUtils.poisson(dailySalesRate);
    }

    // Simulate sales for a month (30 days)
    public int[] simulateMonthlySales() {
        int[] monthlySales = new int[30];
        for (int day = 0; day < 30; day++) {
            monthlySales[day] = simulateDailySales();
        }
        return monthlySales;
    }

    // Update daily sales rate to simulate seasonal changes or trends
    public void updateDailySalesRate(double newRate) {
        this.dailySalesRate = newRate;
    }

    // Getters for testing
    public double getDailySalesRate() {
        return dailySalesRate;
    }
}

