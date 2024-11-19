package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.util.DistributionUtils;
import org.springframework.stereotype.Component;

@Component
public class HumiditySimulator implements BaseMetricSimulator {
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

