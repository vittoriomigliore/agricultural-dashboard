package it.vittoriomigliore.agriculturaldashboard.livedata.chart.companychart;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.livedata.chart.common.IDatasetDto;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CropDatasetDto implements IDatasetDto {
    Crop crop;
    List<BigDecimal> data;

    public CropDatasetDto(Crop crop) {
        this.crop = crop;
        this.data = new ArrayList<>();
    }

    public String getLabel() {
        return crop.getName();
    }

    public Map<String, Integer> getBackgroundColor() {
        Color color = crop.getColor();

        Map<String, Integer> rgb = new HashMap<>();
        rgb.put("r", color.getRed());
        rgb.put("g", color.getGreen());
        rgb.put("b", color.getBlue());
        return rgb;
    }

    @Override
    public void addValue(BigDecimal value) {
        data.add(value);
    }

    public List<BigDecimal> getData() {
        return data;
    }
}
