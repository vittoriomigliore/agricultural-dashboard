package it.vittoriomigliore.agriculturaldashboard.livedata.chart.fieldchart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Weather;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import it.vittoriomigliore.agriculturaldashboard.core.service.IrrigationService;
import it.vittoriomigliore.agriculturaldashboard.core.service.WeatherService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldBuilder {

    private final FieldService fieldService;
    private final IrrigationService irrigationService;
    private final WeatherService weatherService;

    FieldBuilder(FieldService fieldService, IrrigationService irrigationService, WeatherService weatherService) {
        this.fieldService = fieldService;
        this.irrigationService = irrigationService;
        this.weatherService = weatherService;
    }

    public List<FieldChartsDto> getFieldCharts() {
        List<FieldChartsDto> fieldChartsDtos = new ArrayList<>();
        List<Field> fields = fieldService.getAllFields();
        for (Field field : fields) {
            FieldChartsDto fieldChartsDto = new FieldChartsDto(field);

            List<Weather> weatherList = weatherService.getLastFiveWeatherByField(field);
            weatherList.forEach((fieldChartsDto::addWeather));

            List<Irrigation> irrigationList = irrigationService.getLastFiveIrrigationByField(field);
            irrigationList.forEach((fieldChartsDto::addIrrigation));

            fieldChartsDtos.add(fieldChartsDto);
        }

        return fieldChartsDtos;
    }
}
