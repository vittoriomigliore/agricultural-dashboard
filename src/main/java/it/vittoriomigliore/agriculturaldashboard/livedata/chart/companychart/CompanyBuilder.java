package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.service.*;
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
    private final FieldService fieldService;

    public CompanyBuilder(CropService cropService, ProductionService productionService, CostService costService, SaleService saleService, FieldService fieldService) {
        this.cropService = cropService;
        this.productionService = productionService;
        this.costService = costService;
        this.saleService = saleService;
        this.fieldService = fieldService;
    }

    public List<ICompanyChartDto> getCompanyCharts() {
        return List.of(getCropProductionChart(), getProductionVsSalesChart(), getCostChart());
    }

    private ICompanyChartDto getCropProductionChart() {
        CropChartDto cropProductionChart = new CropChartDto(EChartType.CROP_PRODUCTION);

        List<Field> fields = fieldService.getAllFields();

        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {
            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            cropProductionChart.addDateTime(firstDayOfMonth);

            for (Field field : fields) {
                BigDecimal monthCropProduction = productionService.productionSumByFieldAndMonth(field, month);
                cropProductionChart.addValue(field.getCrop(), monthCropProduction);
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

            BigDecimal monthProduction = productionService.productionSumByMonth(month);
            BigDecimal monthSales = saleService.salesSumByMonth(month);

            productionVsSalesChart.addProductionValue(monthProduction);
            productionVsSalesChart.addSalesValue(monthSales);
        }
        return productionVsSalesChart;
    }

    private ICompanyChartDto getCostChart() {
        CropChartDto costChart = new CropChartDto(EChartType.CROP_COST);

        List<Field> fields = fieldService.getAllFields();

        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {
            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            costChart.addDateTime(firstDayOfMonth);

            for (Field field : fields) {
                costChart.addValue(field.getCrop(), costService.costsSumByFieldAndMonth(field, month));
            }
        }

        return costChart;
    }

}
