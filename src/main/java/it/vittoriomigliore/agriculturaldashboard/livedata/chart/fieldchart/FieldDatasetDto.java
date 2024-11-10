package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDatasetDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FieldDatasetDto implements ChartDatasetDto {
    String label;
    List<BigDecimal> data;

    public FieldDatasetDto(String label) {
        this.label = label;
        data = new ArrayList<>();
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Map<String, Integer> getBackgroundColor() {
        return null;
    }

    @Override
    public List<BigDecimal> getData() {
        return data;
    }
}
