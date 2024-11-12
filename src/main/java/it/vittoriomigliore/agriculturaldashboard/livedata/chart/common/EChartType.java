package it.vittoriomigliore.agriculturaldashboard.livedata.chart.common;

public enum EChartType {
    TEMPERATURE,
    HUMIDITY,
    WIND_SPEED,
    PRECIPITATION,
    IRRIGATION,
    CROP_COST,
    CROP_PRODUCTION,
    PRODUCTION_VS_SALES;

    public String getValue() {
        return switch (this) {
            case TEMPERATURE -> "temperature";
            case HUMIDITY -> "humidity";
            case WIND_SPEED -> "windSpeed";
            case PRECIPITATION -> "precipitation";
            case IRRIGATION -> "irrigation";
            case CROP_COST -> "crop-cost";
            case CROP_PRODUCTION -> "crop-production";
            case PRODUCTION_VS_SALES -> "production-vs-sales";
        };
    }

    public String getLabel() {
        return switch (this) {
            case TEMPERATURE -> "Temperature (°C)";
            case HUMIDITY -> "Humidity (%)";
            case WIND_SPEED -> "Wind Speed (km/h)";
            case PRECIPITATION -> "Precipitation (mm)";
            case IRRIGATION -> "Irrigation Volume (L)";
            case CROP_COST -> "Cost";
            case CROP_PRODUCTION -> "Production";
            case PRODUCTION_VS_SALES -> "Sales";
        };
    }

}
