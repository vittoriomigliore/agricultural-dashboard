package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart.CompanyChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart.FieldChartsDto;

import java.util.ArrayList;
import java.util.List;

public class PayloadDto {
    List<FieldChartsDto> fieldCharts;
    List<CompanyChartDto> companyCharts;

    public PayloadDto() {
        fieldCharts = new ArrayList<>();
        companyCharts = new ArrayList<>();
    }

    public void addFieldChart(FieldChartsDto fieldChart) {
        fieldCharts.add(fieldChart);
    }

    public void addCompanyChart(CompanyChartDto companyChart) {
        companyCharts.add(companyChart);
    }

    public List<FieldChartsDto> getFieldCharts() {
        return fieldCharts;
    }

    public List<CompanyChartDto> getCompanyCharts() {
        return companyCharts;
    }
}
