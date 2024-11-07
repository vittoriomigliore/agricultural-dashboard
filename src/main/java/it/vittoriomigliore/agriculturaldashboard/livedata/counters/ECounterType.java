package it.vittoriomigliore.agriculturaldashboard.livedata.counters;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ECounterType {
    COST("cost"),
    SALES("sales"),
    TODAY_SALES("todaySales");

    private final String value;

    ECounterType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
