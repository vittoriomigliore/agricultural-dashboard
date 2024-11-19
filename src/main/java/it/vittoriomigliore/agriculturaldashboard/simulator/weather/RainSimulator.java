package it.vittoriomigliore.agriculturaldashboard.simulator.weather;

import it.vittoriomigliore.agriculturaldashboard.simulator.BaseMetricSimulator;
import it.vittoriomigliore.agriculturaldashboard.simulator.DistributionUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RainSimulator implements BaseMetricSimulator {
    private double lambda;

    @Override
    public void setParameters(double... parameters) {
        this.lambda = parameters[0];
    }

    @Override
    public Double simulate() {
        return DistributionUtils.generateExponential(lambda);
    }
}

