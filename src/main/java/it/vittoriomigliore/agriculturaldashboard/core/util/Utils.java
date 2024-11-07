package it.vittoriomigliore.agriculturaldashboard.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static Integer calculatePercentageChange(BigDecimal oldValue, BigDecimal newValue) {
        if (oldValue.compareTo(BigDecimal.ZERO) == 0) {
            return newValue.compareTo(BigDecimal.ZERO) > 0 ? 100 : 0;
        }

        BigDecimal difference = newValue.subtract(oldValue);

        BigDecimal percentageChange = difference
                .divide(oldValue, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return percentageChange.setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
