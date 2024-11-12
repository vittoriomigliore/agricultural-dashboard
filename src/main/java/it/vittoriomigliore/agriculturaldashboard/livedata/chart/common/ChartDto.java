package it.vittoriomigliore.agriculturaldashboard.livedata.chart.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

abstract public class ChartDto {
    private final EChartType chartType;
    private final List<LocalDateTime> dateTimes;

    public ChartDto(EChartType chartType) {
        this.chartType = chartType;
        this.dateTimes = new ArrayList<>();
    }

    public void addDateTime(LocalDateTime dateTime) {
        dateTimes.add(dateTime);
    }

    public String getChartType() {
        return chartType.getValue();
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
