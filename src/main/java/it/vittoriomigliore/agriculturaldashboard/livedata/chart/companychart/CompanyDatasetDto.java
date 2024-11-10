package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.util.Utils;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartDatasetDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyDatasetDto implements ChartDatasetDto {
    Crop crop;
    List<BigDecimal> data;

    public CompanyDatasetDto(Crop crop) {
        this.crop = crop;
        this.data = new ArrayList<>();
    }

    public String getLabel() {
        return crop.getName();
    }

    public Map<String, Integer> getBackgroundColor() {
        return Utils.getColorRGB(crop.getName());
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
