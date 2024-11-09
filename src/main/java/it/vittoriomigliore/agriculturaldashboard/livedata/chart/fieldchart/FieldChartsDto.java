package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.EChartType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldChartsDto {
    private final Integer field;
    private final Map<EChartType, FieldChartDto> charts;

    public FieldChartsDto(Field field) {
        this.field = field.getId();
        charts = new HashMap<>();
        charts.put(EChartType.TEMPERATURE, new FieldChartDto(EChartType.TEMPERATURE));
        charts.put(EChartType.PRECIPITATION, new FieldChartDto(EChartType.PRECIPITATION));
        charts.put(EChartType.HUMIDITY, new FieldChartDto(EChartType.HUMIDITY));
        charts.put(EChartType.WIND_SPEED, new FieldChartDto(EChartType.WIND_SPEED));
        charts.put(EChartType.IRRIGATION, new FieldChartDto(EChartType.IRRIGATION));
    }

    public void addWeather(Weather weather) {
        FieldChartDto tempChart = charts.get(EChartType.TEMPERATURE);
        tempChart.addValue(weather.getTemperature());
        tempChart.addDateTime(weather.getDateTime());

        FieldChartDto precipChart = charts.get(EChartType.PRECIPITATION);
        precipChart.addValue(weather.getPrecipitation());
        precipChart.addDateTime(weather.getDateTime());

        FieldChartDto humidityChart = charts.get(EChartType.HUMIDITY);
        humidityChart.addValue(weather.getHumidity());
        humidityChart.addDateTime(weather.getDateTime());

        FieldChartDto windSpeedChart = charts.get(EChartType.WIND_SPEED);
        windSpeedChart.addValue(weather.getWindSpeed());
        windSpeedChart.addDateTime(weather.getDateTime());
    }

    public void addIrrigation(Irrigation irrigation) {
        FieldChartDto irrigationChart = charts.get(EChartType.IRRIGATION);
        irrigationChart.addValue(irrigation.getAmountUsed());
        irrigationChart.addDateTime(irrigation.getDateTime());
    }

    public Integer getField() {
        return field;
    }

    public List<FieldChartDto> getCharts() {
        return charts.values().stream().toList();
    }
}
