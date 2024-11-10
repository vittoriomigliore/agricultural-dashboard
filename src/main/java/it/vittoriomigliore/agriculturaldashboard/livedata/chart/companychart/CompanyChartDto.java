package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.EChartType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyChartDto extends ChartDto {

    Map<Crop, CompanyDatasetDto> map;

    public CompanyChartDto(EChartType chartType) {
        super(chartType);
        map = new HashMap<>();
    }

    public void addValue(Crop crop, BigDecimal value) {
        CompanyDatasetDto companyDataset = map.computeIfAbsent(crop, CompanyDatasetDto::new);
        companyDataset.addValue(value);
    }

    public List<CompanyDatasetDto> getDatasets() {
        return map.values().stream().sorted((CompanyDatasetDto o1, CompanyDatasetDto o2) -> o2.crop.getName().compareTo(o1.crop.getName())).toList();
    }

}
