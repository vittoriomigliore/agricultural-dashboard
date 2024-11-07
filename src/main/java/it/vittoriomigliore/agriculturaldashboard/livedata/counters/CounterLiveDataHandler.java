package it.vittoriomigliore.agriculturaldashboard.livedata.counters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.vittoriomigliore.agriculturaldashboard.core.service.CostService;
import it.vittoriomigliore.agriculturaldashboard.core.service.SaleService;
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
public class CounterLiveDataHandler extends TextWebSocketHandler {

    private final CostService costService;
    private final SaleService saleService;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Autowired
    public CounterLiveDataHandler(CostService costService, SaleService saleService, ObjectMapper objectMapper) {
        this.costService = costService;
        this.saleService = saleService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO: improve logging TRACE

        sessions.add(session);
        String jsonData = getPayload();

        session.sendMessage(new TextMessage(jsonData));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO: improve logging TRACE

        sessions.remove(session);
    }

    @Scheduled(fixedRateString = "${counter.update.rate}")
    public void broadcastData() throws IOException {
        // TODO: improve logging TRACE


        String jsonData = getPayload();

        System.out.println("Sending data to WebSocket clients: " + jsonData);

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(jsonData));
            }
        }
    }

    private String getPayload() {
        List<CounterDto> counterDtoList = new ArrayList<>();

        CounterDto costCounter = new CounterDto(ECounterType.COST);
        costCounter.update(costService.costsSum(), costService.costsSumTo10DaysAgo());
        counterDtoList.add(costCounter);

        CounterDto salesCounter = new CounterDto(ECounterType.SALES);
        salesCounter.update(saleService.salesSum(), saleService.salesSumTo10DaysAgo());
        counterDtoList.add(salesCounter);

        CounterDto todaySalesCounter = new CounterDto(ECounterType.TODAY_SALES);
        todaySalesCounter.update(saleService.todaySalesSum(), saleService.yesterdaySalesSum());
        counterDtoList.add(todaySalesCounter);

        try {
            return objectMapper.writeValueAsString(counterDtoList);
        } catch (JsonProcessingException e) {
            // TODO: improve logging
            e.printStackTrace();
            return "[]";
        }
    }
}
