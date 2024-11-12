package it.vittoriomigliore.agriculturaldashboard.livedata.chart.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IDatasetDto {

    void addValue(BigDecimal value);

    String getLabel();

    Map<String, Integer> getBackgroundColor();

    List<BigDecimal> getData();
}
