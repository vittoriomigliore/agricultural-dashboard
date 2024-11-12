package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.BaseDatasetDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.ChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.EChartType;

import java.math.BigDecimal;

public class FieldChartDto extends ChartDto {

    BaseDatasetDto dataset;

    public FieldChartDto(EChartType chartType) {
        super(chartType);
        this.dataset = new BaseDatasetDto(chartType.getLabel());
    }

    public void addValue(BigDecimal value) {
        dataset.addValue(value);
    }

    public BaseDatasetDto getDataset() {
        return dataset;
    }


}
