package it.vittoriomigliore.agriculturaldashboard.simulator;

import it.vittoriomigliore.agriculturaldashboard.simulator.util.DistributionUtils;
import org.springframework.stereotype.Component;

@Component
public class SaleSimulator implements BaseMetricSimulator {

    private double lambda; // Average rate of sales

    @Override
    public void setParameters(double... parameters) {
        this.lambda = parameters[0]; // Expecting lambda to be passed
    }

    @Override
    public Integer simulate() {
        return DistributionUtils.generatePoisson(lambda);
    }
}

