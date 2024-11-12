package it.vittoriomigliore.agriculturaldashboard.livedata.chart.common;

import it.vittoriomigliore.agriculturaldashboard.core.util.Utils;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDatasetDto implements IDatasetDto {
    String label;
    List<BigDecimal> data;

    public BaseDatasetDto(String label) {
        this.label = label;
        data = new ArrayList<>();
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Map<String, Integer> getBackgroundColor() {
        Color color = Utils.getColorFromString(label);

        Map<String, Integer> rgb = new HashMap<>();
        rgb.put("r", color.getRed());
        rgb.put("g", color.getGreen());
        rgb.put("b", color.getBlue());
        return rgb;
    }

    @Override
    public List<BigDecimal> getData() {
        return data;
    }
}
