package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ChartDatasetDto {

    void addValue(BigDecimal value);

    String getLabel();

    Map<String, Integer> getBackgroundColor();

    List<BigDecimal> getData();
}
