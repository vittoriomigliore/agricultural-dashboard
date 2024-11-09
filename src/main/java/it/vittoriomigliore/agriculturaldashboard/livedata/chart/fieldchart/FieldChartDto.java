package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.EChartType;

import java.math.BigDecimal;

public class FieldChartDto extends ChartDto {

    FieldDatasetDto dataset;

    public FieldChartDto(EChartType chartType) {
        super(chartType);
        this.dataset = new FieldDatasetDto();
    }

    public void addValue(BigDecimal value) {
        dataset.addValue(value);
    }

    public FieldDatasetDto getDataset() {
        return dataset;
    }


}
