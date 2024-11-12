package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import it.vittoriomigliore.agriculturaldashboard.core.service.CostService;
import it.vittoriomigliore.agriculturaldashboard.core.service.CropService;
import it.vittoriomigliore.agriculturaldashboard.core.service.ProductionService;
import it.vittoriomigliore.agriculturaldashboard.core.service.SaleService;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.EChartType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
public class CompanyBuilder {
    private final CropService cropService;
    private final ProductionService productionService;
    private final CostService costService;
    private final SaleService saleService;

    public CompanyBuilder(CropService cropService, ProductionService productionService, CostService costService, SaleService saleService) {
        this.cropService = cropService;
        this.productionService = productionService;
        this.costService = costService;
        this.saleService = saleService;
    }

    public List<ICompanyChartDto> getCompanyCharts() {
        return List.of(getCropProductionChart(), getProductionVsSalesChart(), getCostChart());
    }

    private ICompanyChartDto getCropProductionChart() {
        CropChartDto cropProductionChart = new CropChartDto(EChartType.CROP_PRODUCTION);

        List<Crop> crops = cropService.getAllCrops();

        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {
            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            cropProductionChart.addDateTime(firstDayOfMonth);

            for (Crop crop : crops) {
                List<Production> productions = productionService.getAllProductionsByCropAndMonth(crop, month);
                BigDecimal monthCropProduction = productions.stream().map(Production::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
                cropProductionChart.addValue(crop, monthCropProduction);
            }
        }

        return cropProductionChart;
    }

    private ICompanyChartDto getProductionVsSalesChart() {
        ProductionVsSalesChartDto productionVsSalesChart = new ProductionVsSalesChartDto(EChartType.PRODUCTION_VS_SALES);
        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {
            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            productionVsSalesChart.addDateTime(firstDayOfMonth);

            List<Production> productionList = productionService.getAllProductionsByMonth(month);
            BigDecimal monthProduction = productionList.stream().map(Production::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal monthSales = saleService.salesSumByMonth(month);

            productionVsSalesChart.addProductionValue(monthProduction);
            productionVsSalesChart.addSalesValue(monthSales);
        }
        return productionVsSalesChart;
    }

    private ICompanyChartDto getCostChart() {
        CropChartDto costChart = new CropChartDto(EChartType.CROP_COST);

        List<Crop> crops = cropService.getAllCrops();

        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {
            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            costChart.addDateTime(firstDayOfMonth);

            for (Crop crop : crops) {
                costChart.addValue(crop, costService.costsSumByCropAndMonth(crop, month));
            }
        }

        return costChart;
    }

}
