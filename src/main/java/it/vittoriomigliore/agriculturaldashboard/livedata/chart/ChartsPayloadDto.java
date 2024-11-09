package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartsPayloadDto {
    List<FieldChartsDto> fieldCharts;
    List<ChartDto> companyCharts;

    public ChartsPayloadDto() {
        fieldCharts = new ArrayList<>();
        companyCharts = new ArrayList<>();
    }

    public void addFieldChart(FieldChartsDto fieldChart) {
        fieldCharts.add(fieldChart);
    }

    public void addCompanyChart(ChartDto companyChart) {
        companyCharts.add(companyChart);
    }

    public List<FieldChartsDto> getFieldCharts() {
        return fieldCharts;
    }

    public List<ChartDto> getCompanyCharts() {
        return companyCharts;
    }
}
