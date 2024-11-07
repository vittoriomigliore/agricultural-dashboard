package it.vittoriomigliore.agriculturaldashboard.livedata.chart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;

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
        ChartDto tempChart = charts.get(EChartType.TEMPERATURE);
        tempChart.addValue(weather.getTemperature());
        tempChart.addDateTime(weather.getDateTime());

        ChartDto precipChart = charts.get(EChartType.PRECIPITATION);
        precipChart.addValue(weather.getPrecipitation());
        precipChart.addDateTime(weather.getDateTime());

        ChartDto humidityChart = charts.get(EChartType.HUMIDITY);
        humidityChart.addValue(weather.getHumidity());
        humidityChart.addDateTime(weather.getDateTime());

        ChartDto windSpeedChart = charts.get(EChartType.WIND_SPEED);
        windSpeedChart.addValue(weather.getWindSpeed());
        windSpeedChart.addDateTime(weather.getDateTime());
    }

    public Integer getField() {
        return field;
    }

    public List<ChartDto> getCharts() {
        return charts.values().stream().toList();
    }
}