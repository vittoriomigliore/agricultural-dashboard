package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChartDto {
    private final EChartType chartType;
    private final List<BigDecimal> data;
    private final List<LocalDateTime> dateTimes;

    public ChartDto(EChartType chartType) {
        this.chartType = chartType;
        this.data = new ArrayList<>();
        this.dateTimes = new ArrayList<>();
    }

    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public void addDateTime(LocalDateTime dateTime) {
        dateTimes.add(dateTime);
    }

    public EChartType getChartType() {
        return chartType;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
