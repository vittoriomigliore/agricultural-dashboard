package it.vittoriomigliore.agriculturaldashboard.simulator;

public interface BaseMetricSimulator {
    void setParameters(double... parameters);
    Object simulate();
}
