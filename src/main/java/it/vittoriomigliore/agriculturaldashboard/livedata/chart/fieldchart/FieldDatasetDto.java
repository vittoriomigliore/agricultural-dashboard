package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDatasetDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FieldDatasetDto implements ChartDatasetDto {
    List<BigDecimal> data;

    public FieldDatasetDto() {
        data = new ArrayList<>();
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
