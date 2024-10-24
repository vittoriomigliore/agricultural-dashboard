package it.vittoriomigliore.agriculturaldashboard.simulator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class SalesSimulatorTest {

    private SalesSimulator salesSimulator;

    @BeforeEach
    public void setUp() {
        // Setting an initial sales rate of 10 sales per day
        salesSimulator = new SalesSimulator(10.0);
    }

    // Test to check if daily sales are generated correctly
    @Test
    public void testSimulateDailySales() {
        int sales = salesSimulator.simulateDailySales();
        // Ensure sales count is non-negative
        assertTrue("Sales count should be non-negative", sales >= 0);
    }

    // Test to simulate monthly sales
    @Test
    public void testSimulateMonthlySales() {
        int[] monthlySales = salesSimulator.simulateMonthlySales();

        assertEquals(30, monthlySales.length, "Monthly sales array should contain 30 days");

        for (int dailySales : monthlySales) {
            // Ensure sales count is non-negative for each day
            assertTrue("Sales count should be non-negative", dailySales >= 0);
        }
    }

    // Test to check if sales rate updates correctly
    @Test
    public void testUpdateDailySalesRate() {
        double newRate = 15.0;
        salesSimulator.updateDailySalesRate(newRate);

        assertEquals(newRate,
                salesSimulator.getDailySalesRate(), "Sales rate should be updated to the new value");
    }

    // Test to verify sales distribution is reasonable
    @Test
    public void testSalesDistribution() {
        int totalSales = 0;
        int samples = 1000;

        for (int i = 0; i < samples; i++) {
            totalSales += salesSimulator.simulateDailySales();
        }

        double averageSales = totalSales / (double) samples;
        // Check if the average is close to the initial sales rate
        assertEquals(10.0, averageSales, 1.5, "Average sales should be close to 10");
    }
}
