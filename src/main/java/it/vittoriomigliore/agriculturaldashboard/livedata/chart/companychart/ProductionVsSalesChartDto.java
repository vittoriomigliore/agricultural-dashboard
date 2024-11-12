package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.ChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.EChartType;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.BaseDatasetDto;

import java.math.BigDecimal;
import java.util.List;

public class ProductionVsSalesChartDto extends ChartDto implements ICompanyChartDto {

    BaseDatasetDto productionDataset;
    BaseDatasetDto salesDataset;

    public ProductionVsSalesChartDto(EChartType chartType) {
        super(chartType);
        this.productionDataset = new BaseDatasetDto("Production Quantity");
        this.salesDataset = new BaseDatasetDto("Sales Quantity");
    }

    public void addProductionValue(BigDecimal value) {
        productionDataset.addValue(value);
    }

    public void addSalesValue(BigDecimal value) {
        salesDataset.addValue(value);
    }

    public List<BaseDatasetDto> getDatasets() {
        return List.of(productionDataset, salesDataset);
    }

}
