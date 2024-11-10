package it.vittoriomigliore.agriculturaldashboard.core.util;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static Color getColorFromString(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));

            int r = Byte.toUnsignedInt(hash[0]);
            int g = Byte.toUnsignedInt(hash[1]);
            int b = Byte.toUnsignedInt(hash[2]);

            return new Color(r, g, b);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!", e);
        }
    }
}
