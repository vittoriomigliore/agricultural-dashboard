package it.vittoriomigliore.agriculturaldashboard.simulator;

import it.vittoriomigliore.agriculturaldashboard.simulator.utils.DistributionUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductionSimulator implements BaseMetricSimulator {
    private double mean;
    private double standardDeviation;

    @Override
    public void setParameters(double... parameters) {
        this.mean = parameters[0];
        this.standardDeviation = parameters[1];
    }

    @Override
    public Double simulate() {
        return DistributionUtils.generateGaussian(mean, standardDeviation);
    }
}
