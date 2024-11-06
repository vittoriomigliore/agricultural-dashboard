package it.vittoriomigliore.agriculturaldashboard.chart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartDto {
    private final EChartType chartType;
    private final List<BigDecimal> data;

    public ChartDto(EChartType chartType) {
        this.chartType = chartType;
        this.data = new ArrayList<>();
    }

    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public EChartType getChartType() {
        return chartType;
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
