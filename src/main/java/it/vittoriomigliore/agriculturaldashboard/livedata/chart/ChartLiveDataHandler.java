package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import it.vittoriomigliore.agriculturaldashboard.core.service.IrrigationService;
import it.vittoriomigliore.agriculturaldashboard.core.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ChartLiveDataHandler extends TextWebSocketHandler {

    private final FieldService fieldService;
    private final WeatherService weatherService;
    private final IrrigationService irrigationService;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Autowired
    public ChartLiveDataHandler(FieldService fieldService, WeatherService weatherService, IrrigationService irrigationService, ObjectMapper objectMapper) {
        this.fieldService = fieldService;
        this.weatherService = weatherService;
        this.irrigationService = irrigationService;
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
        ChartsPayloadDto chartsPayloadDto = new ChartsPayloadDto();

        List<Field> fields = fieldService.getAllFields();
        for (Field field : fields) {
            FieldChartsDto fieldChartsDto = new FieldChartsDto(field);

            List<Weather> weatherList = weatherService.getLastFiveMinutesWeatherByField(field);
            weatherList.forEach((fieldChartsDto::addWeather));

            List<Irrigation> irrigationList = irrigationService.getLastFiveMinutesIrrigationByField(field);
            irrigationList.forEach((fieldChartsDto::addIrrigation));

            chartsPayloadDto.addFieldChart(fieldChartsDto);
        }

        try {
            return objectMapper.writeValueAsString(chartsPayloadDto);
        } catch (JsonProcessingException e) {
            // TODO: improve logging
            e.printStackTrace();
            return "[]";
        }
    }
}
