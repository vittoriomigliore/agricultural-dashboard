package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

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

    public String getValue() {
        return switch (this) {
            case TEMPERATURE -> "temperature";
            case HUMIDITY -> "humidity";
            case WIND_SPEED -> "windSpeed";
            case PRECIPITATION -> "precipitation";
            case IRRIGATION -> "irrigation";
            case COST -> "cost";
            case PRODUCTION -> "production";
            case SALES -> "sales";
        };
    }

    public String getLabel() {
        return switch (this) {
            case TEMPERATURE -> "Temperature (°C)";
            case HUMIDITY -> "Humidity (%)";
            case WIND_SPEED -> "Wind Speed (km/h)";
            case PRECIPITATION -> "Precipitation (mm)";
            case IRRIGATION -> "Irrigation Volume (L)";
            case COST -> "Cost";
            case PRODUCTION -> "Production";
            case SALES -> "Sales";
        };
    }

}
