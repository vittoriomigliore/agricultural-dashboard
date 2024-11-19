package it.vittoriomigliore.agriculturaldashboard.simulator;

import it.vittoriomigliore.agriculturaldashboard.simulator.util.DistributionUtils;
import org.springframework.stereotype.Component;

@Component
public class IrrigationSimulator implements BaseMetricSimulator {
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
