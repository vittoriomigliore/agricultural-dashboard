package it.vittoriomigliore.agriculturaldashboard.simulator.metric;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.DistributionUtils;
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
