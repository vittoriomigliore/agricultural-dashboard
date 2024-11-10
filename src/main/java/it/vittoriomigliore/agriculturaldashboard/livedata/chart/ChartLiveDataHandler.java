package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart.CompanyBuilder;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart.FieldBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ChartLiveDataHandler extends TextWebSocketHandler {
    private final FieldBuilder fieldBuilder;
    private final CompanyBuilder companyBuilder;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Autowired
    public ChartLiveDataHandler(FieldBuilder fieldBuilder, CompanyBuilder companyBuilder,
                                ObjectMapper objectMapper) {
        this.fieldBuilder = fieldBuilder;
        this.companyBuilder = companyBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        String jsonData = getPayload();

        session.sendMessage(new TextMessage(jsonData));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
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

        payloadDto.fieldCharts = fieldBuilder.getFieldCharts();
        payloadDto.companyCharts = companyBuilder.getCompanyCharts();

        try {
            return objectMapper.writeValueAsString(payloadDto);
        } catch (JsonProcessingException e) {
            // TODO: improve logging
            e.printStackTrace();
            return "{}";
        }
    }

}
