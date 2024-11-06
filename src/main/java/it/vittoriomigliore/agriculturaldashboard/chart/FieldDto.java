package it.vittoriomigliore.agriculturaldashboard.chart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.util.EChartType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldDto {
    private final Integer field;
    private final Map<EChartType, ChartDto> charts;

    public FieldDto(Field field) {
        this.field = field.getId();
        charts = new HashMap<>();
        charts.put(EChartType.TEMPERATURE, new ChartDto(EChartType.TEMPERATURE));
        charts.put(EChartType.PRECIPITATION, new ChartDto(EChartType.PRECIPITATION));
        charts.put(EChartType.HUMIDITY, new ChartDto(EChartType.HUMIDITY));
        charts.put(EChartType.WIND_SPEED, new ChartDto(EChartType.WIND_SPEED));
    }

    public void addWeather(Weather weather) {
        charts.get(EChartType.TEMPERATURE).addValue(weather.getTemperature());
        charts.get(EChartType.PRECIPITATION).addValue(weather.getPrecipitation());
        charts.get(EChartType.HUMIDITY).addValue(weather.getHumidity());
        charts.get(EChartType.WIND_SPEED).addValue(weather.getWindSpeed());
    }

    public Integer getField() {
        return field;
    }

    public List<ChartDto> getCharts() {
        return charts.values().stream().toList();
    }
}
