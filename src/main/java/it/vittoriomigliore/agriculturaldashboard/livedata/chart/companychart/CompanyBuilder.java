package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Production;
import it.vittoriomigliore.agriculturaldashboard.core.service.CropService;
import it.vittoriomigliore.agriculturaldashboard.core.service.ProductionService;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.EChartType;
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

    public CompanyBuilder(CropService cropService, ProductionService productionService) {
        this.cropService = cropService;
        this.productionService = productionService;
    }

    public List<CompanyChartDto> getCompanyCharts() {

        CompanyChartDto costChart = new CompanyChartDto(EChartType.COST);
        CompanyChartDto productionChart = new CompanyChartDto(EChartType.PRODUCTION);
        CompanyChartDto salesChart = new CompanyChartDto(EChartType.SALES);

        List<Crop> crops = cropService.getAllCrops();

        Month currentMonth = LocalDate.now().getMonth();
        List<Month> months = List.of(currentMonth.minus(3), currentMonth.minus(2), currentMonth.minus(1), currentMonth);

        for (Month month : months) {

            LocalDateTime firstDayOfMonth = LocalDateTime.of(LocalDate.now().getYear(), month.getValue(), 1, 0, 0);
            costChart.addDateTime(firstDayOfMonth);
            productionChart.addDateTime(firstDayOfMonth);
            salesChart.addDateTime(firstDayOfMonth);

            for (Crop crop : crops) {
                // TODO: query costs
                BigDecimal monthCost = new BigDecimal(10);
                costChart.addValue(crop, monthCost);

                List<Production> productions = productionService.getAllProductionsByCropAndMonth(crop, month);
                BigDecimal monthProduction = productions.stream().map(Production::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
                productionChart.addValue(crop, monthProduction);

                // TODO: query sales
                BigDecimal monthSales = new BigDecimal(5);
                salesChart.addValue(crop, monthSales);
            }
        }

        return List.of(productionChart, salesChart, costChart);
    }

}
