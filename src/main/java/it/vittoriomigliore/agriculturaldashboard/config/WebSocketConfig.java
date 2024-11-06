package it.vittoriomigliore.agriculturaldashboard.config;

import it.vittoriomigliore.agriculturaldashboard.chart.ChartLiveDataHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChartLiveDataHandler chartLiveDataHandler;

    public WebSocketConfig(ChartLiveDataHandler chartLiveDataHandler) {
        this.chartLiveDataHandler = chartLiveDataHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chartLiveDataHandler, "/charts-live-data").setAllowedOrigins("*");
    }
}
