package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.ChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.EChartType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CropChartDto extends ChartDto implements ICompanyChartDto {

    Map<Crop, CropDatasetDto> map;

    public CropChartDto(EChartType chartType) {
        super(chartType);
        map = new HashMap<>();
    }

    public void addValue(Crop crop, BigDecimal value) {
        CropDatasetDto companyDataset = map.computeIfAbsent(crop, CropDatasetDto::new);
        companyDataset.addValue(value);
    }

    public List<CropDatasetDto> getDatasets() {
        return map.values().stream().sorted((CropDatasetDto o1, CropDatasetDto o2) -> o2.crop.getName().compareTo(o1.crop.getName())).toList();
    }

}
