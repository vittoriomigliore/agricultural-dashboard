package it.vittoriomigliore.agriculturaldashboard.config;

import it.vittoriomigliore.agriculturaldashboard.livedata.chart.ChartLiveDataHandler;
import it.vittoriomigliore.agriculturaldashboard.livedata.counters.CounterLiveDataHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChartLiveDataHandler chartLiveDataHandler;
    private final CounterLiveDataHandler counterLiveDataHandler;

    public WebSocketConfig(ChartLiveDataHandler chartLiveDataHandler,
                           CounterLiveDataHandler counterLiveDataHandler) {
        this.chartLiveDataHandler = chartLiveDataHandler;
        this.counterLiveDataHandler = counterLiveDataHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chartLiveDataHandler, "/charts-live-data").setAllowedOrigins("*");
        registry.addHandler(counterLiveDataHandler, "/counters-live-data").setAllowedOrigins("*");
    }
}
