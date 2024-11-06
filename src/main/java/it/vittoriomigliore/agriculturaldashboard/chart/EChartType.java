package it.vittoriomigliore.agriculturaldashboard.chart;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EChartType {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    WIND_SPEED("windSpeed"),
    PRECIPITATION("precipitation"),
    IRRIGATION("irrigation"),
    COST("cost"),
    PRODUCTION("production"),
    SALES("sales");

    private final String value;

    EChartType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
