package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.vittoriomigliore.agriculturaldashboard.core.entity.*;
import it.vittoriomigliore.agriculturaldashboard.core.service.*;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart.CompanyChartDto;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart.FieldChartsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ChartLiveDataHandler extends TextWebSocketHandler {

    private final FieldService fieldService;
    private final WeatherService weatherService;
    private final IrrigationService irrigationService;
    private final CropService cropService;
    private final ProductionService productionService;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Autowired
    public ChartLiveDataHandler(FieldService fieldService, WeatherService weatherService,
                                IrrigationService irrigationService, CropService cropService, ProductionService productionService,
                                ObjectMapper objectMapper) {
        this.fieldService = fieldService;
        this.weatherService = weatherService;
        this.irrigationService = irrigationService;
        this.cropService = cropService;
        this.productionService = productionService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        String jsonData = getPayload();

        // TODO: improve logging TRACE
        System.out.println("Sending data to WebSocket clients: " + jsonData);

        session.sendMessage(new TextMessage(jsonData));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);

        // TODO: improve logging TRACE
    }

    @Scheduled(fixedRateString = "${chart.update.rate}")
    public void broadcastData() throws IOException {

        String jsonData = getPayload();

        // TODO: improve logging TRACE
        System.out.println("Sending data to WebSocket clients: " + jsonData);

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(jsonData));
            }
        }
    }

    private String getPayload() {
        PayloadDto payloadDto = new PayloadDto();

        addFieldCharts(payloadDto);

        addCompanyCharts(payloadDto);


        try {
            return objectMapper.writeValueAsString(payloadDto);
        } catch (JsonProcessingException e) {
            // TODO: improve logging
            e.printStackTrace();
            return "{}";
        }
    }


    private void addFieldCharts(PayloadDto payloadDto) {
        List<Field> fields = fieldService.getAllFields();
        for (Field field : fields) {
            FieldChartsDto fieldChartsDto = new FieldChartsDto(field);

            List<Weather> weatherList = weatherService.getLastFiveMinutesWeatherByField(field);
            weatherList.forEach((fieldChartsDto::addWeather));

            List<Irrigation> irrigationList = irrigationService.getLastFiveMinutesIrrigationByField(field);
            irrigationList.forEach((fieldChartsDto::addIrrigation));

            payloadDto.addFieldChart(fieldChartsDto);
        }
    }

    private void addCompanyCharts(PayloadDto payloadDto) {
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

        payloadDto.addCompanyChart(costChart);
        payloadDto.addCompanyChart(productionChart);
        payloadDto.addCompanyChart(salesChart);
    }
}
