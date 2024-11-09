package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDatasetDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompanyDatasetDto implements ChartDatasetDto {
    Crop crop;
    List<BigDecimal> data;

    public CompanyDatasetDto(Crop crop) {
        this.crop = crop;
        this.data = new ArrayList<>();
    }

    public String getCrop() {
        return crop.getName();
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
